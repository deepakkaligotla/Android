package `in`.kaligotla.deepak.navigation

import `in`.kaligotla.deepak.core.Constants.AUTH_SCREEN
import `in`.kaligotla.deepak.core.Constants.PROFILE_SCREEN

sealed class Screen(val route: String) {
    object AuthScreen: Screen(AUTH_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
}