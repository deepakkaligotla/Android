package `in`.kaligotla.deepak

import androidx.compose.ui.test.junit4.createComposeRule
import `in`.kaligotla.deepak.presentation.profile.ProfileScreenPreview
import org.junit.Rule
import org.junit.Test

class GoogleOAuthTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun googleOAuthTest() {
        rule.setContent {(ProfileScreenPreview())}
    }
}