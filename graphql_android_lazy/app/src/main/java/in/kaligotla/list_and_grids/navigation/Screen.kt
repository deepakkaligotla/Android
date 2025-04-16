package `in`.kaligotla.list_and_grids.navigation

import `in`.kaligotla.list_and_grids.core.Constants.FIRST_SCREEN

sealed class Screen(val route: String) {
    object FirstScreen: Screen(FIRST_SCREEN)
}