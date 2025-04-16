package `in`.kaligotla.allpermissions.presentation.intro.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import `in`.kaligotla.allpermissions.presentation.intro.IntroNavOption
import `in`.kaligotla.allpermissions.ui.previews.AllScreenPreview

@Composable
fun WelcomeScreen(navController: NavController = rememberNavController()) = IntroCompose(
    navController = navController,
    text = "Welcome",
    showBackButton = false
) {
    navController.navigate(IntroNavOption.MotivationScreen.name)
}


@AllScreenPreview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}

