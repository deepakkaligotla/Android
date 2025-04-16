package `in`.kaligotla.datastructures.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import `in`.kaligotla.datastructures.presentation.NavRoutes
import `in`.kaligotla.datastructures.presentation.about.AboutScreen
import `in`.kaligotla.datastructures.presentation.main.home.myTable.MyTable
import `in`.kaligotla.datastructures.presentation.main.myDataStructures.MyDataStructures
import `in`.kaligotla.datastructures.presentation.main.mySearch.MySearch
import `in`.kaligotla.datastructures.presentation.main.mySort.MySort
import `in`.kaligotla.datastructures.presentation.settings.SettingsScreen

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.mainGraph(navController: NavHostController, drawerState: DrawerState) {
    navigation(startDestination = MainNavOption.MyTable.name, route = NavRoutes.MainRoute.name) {
        composable(MainNavOption.MyTable.name) {
            MyTable(drawerState)
        }

        composable(MainNavOption.MyDataStructures.name) {
            MyDataStructures(drawerState, navController)
        }

        composable(MainNavOption.MySearch.name) {
            MySearch(drawerState, navController)
        }

        composable(MainNavOption.MySort.name) {
            MySort(drawerState, navController)
        }

        composable(MainNavOption.SettingsScreen.name) {
            SettingsScreen(drawerState)
        }

        composable(MainNavOption.AboutScreen.name) {
            AboutScreen(drawerState)
        }
    }
}

enum class MainNavOption {
    MyTable,
    MyDataStructures,
    MySearch,
    MySort,
    AboutScreen,
    SettingsScreen,
}