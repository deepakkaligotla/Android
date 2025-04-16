package `in`.kaligotla.datastructures.screen.myDataStructures

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import `in`.kaligotla.datastructures.presentation.MainActivity
import `in`.kaligotla.datastructures.presentation.MainCompose
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyVectorScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun drawerClickTest() {
        composeTestRule.onNode(hasTestTag("MainCompose"))
            .assertIsDisplayed()
    }

    @Test
    fun myVectorDisplayTest() {
        composeTestRule.onNode(hasTestTag("drawerNavigation"))
            .performClick()

        composeTestRule.onNodeWithText("Data Structures")
            .assertExists().assertHasClickAction().performClick()

//        composeTestRule.onNodeWithTag("myDataStructureTag")
//            .assertIsDisplayed()

//        composeTestRule.onNode(
//            hasText("Vector")
//                    and
//                    hasClickAction()
//        ).assertExists()
    }

}