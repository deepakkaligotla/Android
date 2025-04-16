package `in`.kaligotla.datastructures.presentation.intro.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import `in`.kaligotla.datastructures.presentation.intro.IntroNavOption
import `in`.kaligotla.datastructures.ui.previews.AllScreenPreview
import `in`.kaligotla.datastructures.ui.theme.DatastructuresTheme


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
    DatastructuresTheme {
        MotivationScreen(navController = navController)
    }
}

