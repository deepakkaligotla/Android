package `in`.kaligotla.allpermissions.presentation.intro.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import `in`.kaligotla.allpermissions.presentation.intro.IntroNavOption
import `in`.kaligotla.allpermissions.proto.AppTheme
import `in`.kaligotla.allpermissions.ui.previews.AllScreenPreview
import `in`.kaligotla.allpermissions.ui.theme.AllPermissionsTheme


@Composable
fun MotivationScreen(navController: NavController) = IntroCompose(
    navController = navController,
    text = "Motivation"
) {
    navController.navigate(IntroNavOption.RecommendationScreen.name)
}

@AllScreenPreview
@Composable
fun MotivationPrivacyPreview() {
    val navController = rememberNavController()
    AllPermissionsTheme(appTheme = AppTheme.Default) {
        MotivationScreen(navController = navController)
    }
}

