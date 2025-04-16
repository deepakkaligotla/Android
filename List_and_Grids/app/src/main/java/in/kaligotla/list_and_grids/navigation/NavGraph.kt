package `in`.kaligotla.list_and_grids.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import `in`.kaligotla.list_and_grids.presentation.firstScreen.FirstScreenUI
import `in`.kaligotla.list_and_grids.navigation.Screen.FirstScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = FirstScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = FirstScreen.route
        ) {
            FirstScreenUI(

            )
        }
    }
}