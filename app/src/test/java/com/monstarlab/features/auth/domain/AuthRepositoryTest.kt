package com.monstarlab.features.auth.domain

import com.monstarlab.features.auth.data.api.AuthApi
import com.monstarlab.features.auth.data.api.dtos.TokenResponseDTO
import com.monstarlab.features.auth.data.api.dtos.toAuthToken
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by aslam on 08,March,2023
 */
internal class AuthRepositoryTest {

    private lateinit var mockAuthApi: AuthApi
    private lateinit var authRepository: AuthRepository

    @Before
    fun setUp() {
        mockAuthApi = mockk()
        authRepository = AuthRepository(mockAuthApi)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLoginReturnsExpectedAuthToken() = runTest {

        // Arrange
        val email = "aslam@test.com"
        val password = "password"
        val expectedTokenResponseDTO = TokenResponseDTO("token")
        coEvery { mockAuthApi.postLogin(email, password) } returns expectedTokenResponseDTO

        // Act
        val actualAuthToken = authRepository.login(email, password)

        // Assert
        assertEquals(expectedTokenResponseDTO.toAuthToken(), actualAuthToken)
        coVerify(exactly = 1) { mockAuthApi.postLogin(email, password) }

    }

}
