package `in`.kaligotla.deepak.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import `in`.kaligotla.deepak.navigation.Screen.AuthScreen
import `in`.kaligotla.deepak.navigation.Screen.ProfileScreen
import `in`.kaligotla.deepak.presentation.auth.AuthScreen
import `in`.kaligotla.deepak.presentation.profile.ProfileScreen

@Composable
@ExperimentalAnimationApi
fun NavGraph(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AuthScreen.route,
        enterTransition = {EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = AuthScreen.route
        ) {
            AuthScreen(
                navigateToProfileScreen = {
                    navController.navigate(ProfileScreen.route)
                }
            )
        }
        composable(
            route = ProfileScreen.route
        ) {
            ProfileScreen(
                navigateToAuthScreen = {
                    navController.navigate(AuthScreen.route)
                }
            )
        }
    }
}