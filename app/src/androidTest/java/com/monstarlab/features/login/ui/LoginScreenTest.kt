package com.monstarlab.features.login.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.monstarlab.core.error.ErrorModel
import com.monstarlab.core.network.errorhandling.ApiException
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var state: MutableState<LoginState>

    @Before
    fun setUp() {
        state = mutableStateOf(LoginState())
        composeTestRule.setContent {
            LoginScreen(
                state = state.value,
                actions = LoginActions(
                    onEmailChange = { state.value = state.value.copy(email = it) },
                    onPasswordChange = { state.value = state.value.copy(password = it) },
                    onLoginClick = { state.value = state.value.copy(isLoading = true) },
                ),
            )
        }
    }

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
            .assert(hasText(""))

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
     * - Email field should contain "test@test.com"
     */
    @Test
    fun testEmailInput() {
        // GIVEN
        composeTestRule
            .onNodeWithTag("email")
            .assert(hasText("eve.holt@reqres.in"))

        // WHEN
        composeTestRule
            .onNodeWithTag("email")
            .performTextReplacement("test@test.com")

        // THEN
        composeTestRule
            .onNodeWithTag("email")
            .assert(hasText("test@test.com"))
    }

    /**
     * GIVEN:
     * - Initial password is empty
     * - Test password is "cityslicka", but due to visual transformation, it should not be visible
     * WHEN:
     * - User types "secret" into the password field
     * THEN:
     * - Password field should remain empty (due to visual transformation)
     */
    @Test
    fun testPasswordInput() {
        // GIVEN
        composeTestRule
            .onNodeWithTag("password")
            .assert(hasText(""))

        // WHEN
        composeTestRule
            .onNodeWithTag("password")
            .performTextReplacement("secret")

        // THEN
        composeTestRule
            .onNodeWithTag("password")
            .assert(hasText(""))
    }

    /**
     * GIVEN:
     * - The loading state is false
     * WHEN:
     * - User clicks the login button
     * - The loading state is true (simulating an ongoing login attempt)
     * THEN:
     * - Login button should be disabled
     */
    @Test
    fun testLoginButton_disabledWhenLoading() {
        // GIVEN
        composeTestRule
            .onNodeWithTag("login")
            .assertIsEnabled()

        // WHEN
        composeTestRule
            .onNodeWithTag("login")
            .performClick()

        // THEN
        composeTestRule
            .onNodeWithTag("login")
            .assertIsNotEnabled()
    }

    /**
     * GIVEN:
     * - Initially, no error message is displayed
     * - An error state with "Invalid credentials" message is set
     * WHEN:
     * - The UI recomposes
     * THEN:
     * - An error message with the text "Invalid credentials" should be displayed
     */
    @Test
    fun testErrorState() {
        // GIVEN
        composeTestRule
            .onNodeWithText("Invalid credentials")
            .assertIsNotDisplayed()

        // WHEN
        state.value =
            state.value.copy(error = ErrorModel.ApiError(ApiException(401, "Invalid credentials")))

        // THEN
        composeTestRule
            .onNodeWithText("Invalid credentials")
            .assertIsDisplayed()
    }
}
