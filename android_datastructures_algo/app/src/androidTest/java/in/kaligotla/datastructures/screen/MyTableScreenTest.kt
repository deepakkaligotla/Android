package `in`.kaligotla.datastructures.screen

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import `in`.kaligotla.datastructures.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyTableScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun drawerClickTest() {
        composeTestRule.onNode(hasTestTag("MainCompose"))
            .assertIsDisplayed()
    }

    @Test
    fun myTableDisplayTest() {
        composeTestRule.onNode(hasTestTag("myTableTag"))
            .assertIsDisplayed()
    }

    @Test
    fun myTablePincodeIncorrect() {
        composeTestRule.onNodeWithTag("myTablePincode")
            .performTextInput("abcd")

        composeTestRule.onNodeWithTag("myTableLazyGrid")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Pincode")
            .assert(hasText("City"))
            .assertExists("Incorrect Pincode")
    }

    @Test
    fun myTablePincodeCorrect() {
        composeTestRule.onNodeWithTag("myTablePincode")
            .performTextInput("534202")

        composeTestRule.onNodeWithTag("myTableLazyGrid")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Pincode")
            .assertExists("Correct Pincode")
    }
}