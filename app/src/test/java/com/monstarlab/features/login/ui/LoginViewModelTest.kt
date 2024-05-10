package com.monstarlab.features.login.ui

import app.cash.turbine.test
import com.monstarlab.core.error.ErrorModel
import com.monstarlab.core.network.errorhandling.ApiException
import com.monstarlab.features.login.domain.usecase.LoginUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var viewModel: LoginViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        loginUseCase = mockk()
        viewModel = LoginViewModel(loginUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun testInitialState() = runTest {
        // GIVEN
        val initialState = LoginState()

        // WHEN
        val state = viewModel.stateFlow.value

        // THEN
        assertEquals(initialState, state)
    }

    /**
     * GIVEN
     * - initialEmail is "eve.holt@reqres.in"
     * - testEmail is "test@test.com"
     * WHEN
     * - User changes email
     * THEN
     * - State email is updated
     * - State email equals test email
     */
    @Test
    fun testOnEmailChange() = runTest {
        // GIVEN
        val initialEmail = "eve.holt@reqres.in"
        val testEmail = "test@test.com"
        val initialState = viewModel.stateFlow.value
        assertEquals(initialEmail, initialState.email)

        // WHEN
        viewModel.onEmailChange(testEmail)

        // THEN
        val state = viewModel.stateFlow.value
        assertEquals(testEmail, state.email)
    }


    /**
     * GIVEN
     * - initialPassword is "cityslicka"
     * - testPassword is "password"
     * WHEN
     * - User changes password
     * THEN
     * - State password is updated
     * - State password equals test password
     */
    @Test
    fun testOnPasswordChange() = runTest {
        // GIVEN
        val initialPassword = "cityslicka"
        val testPassword = "password"
        val initialState = viewModel.stateFlow.value
        assertEquals(initialPassword, initialState.password)

        // WHEN
        viewModel.onPasswordChange(testPassword)

        // THEN
        val state = viewModel.stateFlow.value
        assertEquals(testPassword, state.password)
    }

    /**
     * GIVEN
     * - User is not logged in
     * - Loading is false
     * - Error is null
     * WHEN
     * - User logs in
     * THEN
     * - User is logged in
     * - Loading is false
     * - Error is null
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLoginSuccess() = runTest {
        // GIVEN
        val testEmail = "test@test.com"
        val testPassword = "password"

        assertEquals(false, viewModel.stateFlow.value.isLoggedIn)
        assertEquals(false, viewModel.stateFlow.value.isLoading)
        assertEquals(null, viewModel.stateFlow.value.error)
        coEvery { loginUseCase(testEmail, testPassword) } returns Result.success(mockk())

        // WHEN
        viewModel.onEmailChange(testEmail)
        viewModel.onPasswordChange(testPassword)
        viewModel.login()
        advanceUntilIdle()

        // THEN
        val state = viewModel.stateFlow.value
        assertTrue(state.isLoggedIn)
        assertFalse(state.isLoading)
        assertEquals(null, state.error)
    }

    /**
     * GIVEN
     * - User is not logged in
     * - Loading is false
     * - exception is ApiException with code 400 and message "user not found"
     * - expectedError is ErrorModel.ApiError(exception)
     * WHEN
     * - User logs in
     * THEN
     * - User is not logged in
     * - Loading is false
     * - Error is expectedError
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLoginFailure() = runTest {
        // GIVEN
        val testEmail = "test@test.com"
        val testPassword = "password"
        val exception = ApiException(400, "user not found")
        val expectedError = ErrorModel.ApiError(exception)

        assertEquals(false, viewModel.stateFlow.value.isLoggedIn)
        assertEquals(false, viewModel.stateFlow.value.isLoading)
        assertEquals(null, viewModel.stateFlow.value.error)
        coEvery {
            loginUseCase(testEmail, testPassword)
        } returns Result.failure(exception)

        // WHEN
        viewModel.onEmailChange(testEmail)
        viewModel.onPasswordChange(testPassword)
        viewModel.login()
        advanceUntilIdle()

        // THEN
        val state = viewModel.stateFlow.value
        assertFalse(state.isLoggedIn)
        assertFalse(state.isLoading)
        assertEquals(expectedError, state.error)
    }

    /**
     * GIVEN
     * - User is not logged in
     * - Loading is false
     * - exception is ApiException with code 400 and message "user not found"
     * - expectedError is ErrorModel.ApiError(exception)
     * WHEN
     * - User logs in
     * THEN
     * - User is not logged in
     * - Loading is false
     * - Error is expectedError
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLoginLoadingState() = runTest {
        // GIVEN
        val initialState = viewModel.stateFlow.value

        assertEquals(false, initialState.isLoading)
        coEvery { loginUseCase(any(), any()) } coAnswers {
            delay(1000)
            Result.success(mockk())
        }

        // WHEN
        viewModel.login()
        runCurrent()
        val intermediateState = viewModel.stateFlow.value
        assertTrue(intermediateState.isLoading)
        advanceTimeBy(2000)

        // THEN
        val finalState = viewModel.stateFlow.value
        assertFalse(finalState.isLoading)
    }

    /**
     * GIVEN
     * - The initial state is: not loading & not logged in
     * - The login use case always returns a successful result
     * WHEN
     * - User logs in
     * THEN
     * - The initial state is: not loading & not logged in
     * - The state changes to: loading & not logged in
     * - The state changes to: not loading & logged in
     */
    @Test
    fun testLoginStateWithTurbine() = runTest {
        // GIVEN
        coEvery { loginUseCase(any(), any()) } returns Result.success(mockk())

        // WHEN
        viewModel.login()

        // THEN
        viewModel.stateFlow.test {
            assertEquals(LoginState(isLoading = false, isLoggedIn = false), awaitItem())
            assertEquals(LoginState(isLoading = true, isLoggedIn = false), awaitItem())
            assertEquals(LoginState(isLoading = false, isLoggedIn = true), awaitItem())
        }
    }

}