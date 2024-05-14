package com.monstarlab.features.login.domain.usecase

import com.monstarlab.features.auth.domain.models.AuthToken
import com.monstarlab.features.auth.domain.repository.AuthRepository
import com.monstarlab.features.user.domain.repository.UserRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runTest

class LoginUseCaseTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var userRepository: UserRepository
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        authRepository = mockk()
        userRepository = mockk()
        loginUseCase = LoginUseCase(authRepository, userRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    /**
     * GIVEN
     * - Auth token is returned
     * - Retrieving user is returned
     * WHEN
     * - User logs in
     * THEN
     * - Result is successful
     */
    @Test
    fun testLoginSuccess() = runTest {
        // GIVEN
        val email = "test@test.com"
        val password = "password"

        coEvery { authRepository.login(email, password) } returns AuthToken("token")
        coEvery { userRepository.get() } returns mockk()

        // WHEN
        val result = loginUseCase.invoke(email, password)

        // THEN
        assertEquals("Result is successful", true, result.isSuccess)
    }

    /**
     * GIVEN
     * - Auth token throws an exception
     * - Retrieving user is returned
     * WHEN
     * - User logs in
     * THEN
     * - Result is unsuccessful
     */
    @Test
    fun testLoginFailure() = runTest {
        // GIVEN
        val email = "test@test.com"
        val password = "password"

        coEvery { authRepository.login(email, password) } throws Exception()
        coEvery { userRepository.get() } returns mockk()

        // WHEN
        val result = loginUseCase.invoke(email, password)

        // THEN
        assertEquals("Result is unsuccessful", false, result.isSuccess)
    }

    /**
     * GIVEN
     * - Auth token is returned
     * - Retrieving user throws an exception
     * WHEN
     * - User logs in
     * THEN
     * - Result is unsuccessful
     */
    @Test
    fun testLoginFailureWhenUserRetrievalFails() = runTest {
        // GIVEN
        val email = "test@test.com"
        val password = "password"

        coEvery { authRepository.login(email, password) } returns AuthToken("token")
        coEvery { userRepository.get() } throws Exception()

        // WHEN
        val result = loginUseCase.invoke(email, password)

        // THEN
        assertEquals("Result is unsuccessful", false, result.isSuccess)
    }
}
