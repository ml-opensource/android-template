package com.monstarlab.features.login.ui

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    private val mockActions: LoginActions = mockk(relaxed = true)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            LoginScreen(state = LoginState(), actions = mockActions)
        }
    }

    /**
     * GIVEN:
     * - Initial state of the login screen
     * WHEN:
     * - The screen is displayed
     * THEN:
     * - Email field should be enabled, displayed, and contain "eve.holt@reqres.in"
     * - Password field should be enabled and displayed
     * - Login button should be enabled and displayed
     */
    @Test
    fun testInitialState() {
        composeTestRule
            .onNodeWithTag("email")
            .assertIsEnabled()
            .assertIsDisplayed()
            .assert(hasText("eve.holt@reqres.in"))

        composeTestRule
            .onNodeWithTag("password")
            .assertIsEnabled()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("login")
            .assertIsEnabled()
            .assertIsDisplayed()
    }

    /**
     * GIVEN:
     * - Initial email is "eve.holt@reqres.in"
     * - Test email is "test@test.com"
     * WHEN:
     * - User types "test@test.com" into the email field
     * THEN:
     * - onEmailChange action should be called with "test@test.com"
     */
    @Test
    fun testEmailInput() {
        composeTestRule
            .onNodeWithTag("email")
            .performTextReplacement("test@test.com")

        verify { mockActions.onEmailChange("test@test.com") }
    }

    /**
     * GIVEN:
     * - Initial password is empty
     * - Test password is "secret"
     * WHEN:
     * - User types "secret" into the password field
     * THEN:
     * - onPasswordChange action should be called with "secret"
     */
    @Test
    fun testPasswordInput() {
        composeTestRule
            .onNodeWithTag("password")
            .performTextReplacement("secret")

        verify { mockActions.onPasswordChange("secret") }
    }

    /**
     * GIVEN:
     * - User is on the login screen
     * WHEN:
     * - User clicks the login button
     * THEN:
     * - onLoginClick action should be called
     */
    @Test
    fun testLoginButtonClick() {
        composeTestRule.onNodeWithTag("login").performClick()

        verify { mockActions.onLoginClick() }
    }
}
