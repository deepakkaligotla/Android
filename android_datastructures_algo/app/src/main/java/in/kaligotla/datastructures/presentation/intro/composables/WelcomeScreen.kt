package `in`.kaligotla.datastructures.presentation.intro.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import `in`.kaligotla.datastructures.presentation.intro.IntroNavOption
import `in`.kaligotla.datastructures.ui.previews.AllScreenPreview
import `in`.kaligotla.datastructures.ui.theme.DatastructuresTheme

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
    DatastructuresTheme {
        WelcomeScreen()
    }
}

