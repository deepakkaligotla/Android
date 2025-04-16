package `in`.kaligotla.allpermissions.presentation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import `in`.kaligotla.allpermissions.presentation.intro.IntroViewModel
import `in`.kaligotla.allpermissions.presentation.main.mainCommon.MainBottomBar
import `in`.kaligotla.allpermissions.presentation.main.mainCommon.MainTopBar
import `in`.kaligotla.allpermissions.presentation.main.mainCommon.MyNavigationDrawer
import `in`.kaligotla.allpermissions.proto.AppTheme
import `in`.kaligotla.allpermissions.ui.theme.AllPermissionsTheme

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainCompose(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    vm: IntroViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var userTheme by rememberSaveable { mutableStateOf( AppTheme.Default) }
    var myDestinaton = NavRoutes.IntroRoute.name

    LaunchedEffect(LocalContext) {
        myDestinaton = if(vm.onboardState) NavRoutes.MainRoute.name else NavRoutes.IntroRoute.name
        vm.userTheme.observeForever {
            userTheme = it.userTheme
        }
    }

    AllPermissionsTheme(appTheme = userTheme) {
        Scaffold(
            modifier = Modifier.testTag("MainCompose"),
            topBar = { MainTopBar(true, drawerState, vm) },
            bottomBar = { MainBottomBar(true, navController) }
        ) { it
            Surface {
                MyNavigationDrawer(navController, userTheme, drawerState, true)
            }
        }
    }
}

@Composable
fun rememberUserTheme(): AppTheme {
    return AppTheme.Dark
}

enum class NavRoutes {
    IntroRoute,
    MainRoute,
    SubRoute
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MainActivityPreview() {
    MainCompose()
}