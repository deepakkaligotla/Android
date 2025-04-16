package `in`.kaligotla.allpermissions.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import `in`.kaligotla.allpermissions.navigation.Screen.*
import `in`.kaligotla.allpermissions.presentation.NavRoutes
import `in`.kaligotla.allpermissions.proto.AppTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.subGraph(navController: NavHostController, drawerState: DrawerState, userTheme: AppTheme) {
    navigation(startDestination = MainNavOption.MyHome.name, route = NavRoutes.SubRoute.name) {
        composable(SubNavOption.MyVector.name) {
//            MyVector(drawerState)
        }
    }
}

enum class SubNavOption {
    MyVector,
}