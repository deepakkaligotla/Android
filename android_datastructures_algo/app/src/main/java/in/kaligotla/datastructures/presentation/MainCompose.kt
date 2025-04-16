package `in`.kaligotla.datastructures.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import `in`.kaligotla.datastructures.R
import `in`.kaligotla.datastructures.navigation.MainNavOption
import `in`.kaligotla.datastructures.navigation.mainGraph
import `in`.kaligotla.datastructures.navigation.subGraph
import `in`.kaligotla.datastructures.presentation.commonComponents.FindWindowScreen
import `in`.kaligotla.datastructures.presentation.intro.IntroViewModel
import `in`.kaligotla.datastructures.presentation.intro.introGraph
import `in`.kaligotla.datastructures.ui.components.appdrawer.AppDrawerContent
import `in`.kaligotla.datastructures.ui.components.appdrawer.AppDrawerItemInfo
import `in`.kaligotla.datastructures.ui.theme.DatastructuresTheme
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCompose(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    vm: IntroViewModel = hiltViewModel()
) {
    val orientation = LocalConfiguration.current.orientation
    var onboardState by rememberSaveable { mutableStateOf(false) }
    vm.onboardState.observe(LocalLifecycleOwner.current) {
        onboardState = vm.onboardState.value!!
        Log.e("Onboard State", "" + onboardState)
    }

    DatastructuresTheme {
        Scaffold(
            modifier = Modifier.testTag("MainCompose"),
            topBar = {
                if (onboardState) {
                    val contextForToast = LocalContext.current.applicationContext
                    val coroutineScope = rememberCoroutineScope()
                    CenterAlignedTopAppBar(
                        title = { Text(text = "", style = MaterialTheme.typography.headlineSmall) },
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                Toast.makeText(contextForToast, "Add Clicked", Toast.LENGTH_SHORT)
                                    .show()
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Items"
                                )
                            }
                            IconButton(
                                onClick = {
                                    Toast.makeText(
                                        contextForToast,
                                        "Search Clicked",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Black.copy(alpha = 0.3f)
                        )
                    )
                }
            },
            bottomBar = {
                if (onboardState) {
                    BottomAppBar(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        contentPadding = PaddingValues()
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(MainNavOption.MyTable.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                        }

                        IconButton(
                            onClick = {
                                navController.navigate(MainNavOption.SettingsScreen.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Profile"
                            )
                        }

                        IconButton(
                            onClick = {
                                navController.navigate(MainNavOption.AboutScreen.name) {
                                    popUpTo(NavRoutes.MainRoute.name)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "About"
                            )
                        }
                        FindWindowScreen()
                    }
                }
            },
            content = {
                it
                Surface {
                    ModalNavigationDrawer(
                        modifier = Modifier.testTag("drawerNavigation"),
                        drawerState = drawerState,
                        drawerContent = {
                            AppDrawerContent(
                                drawerState = drawerState,
                                menuItems = DrawerParams.drawerButtons,
                                defaultPick = MainNavOption.MyTable
                            ) { onUserPickedOption ->
                                when (onUserPickedOption) {
                                    MainNavOption.MyTable -> {
                                        navController.navigate(onUserPickedOption.name) {
                                            popUpTo(NavRoutes.MainRoute.name)
                                        }
                                    }

                                    MainNavOption.MyDataStructures -> {
                                        navController.navigate(onUserPickedOption.name) {
                                            popUpTo(NavRoutes.MainRoute.name)
                                        }
                                    }

                                    MainNavOption.MySearch -> {
                                        navController.navigate(onUserPickedOption.name) {
                                            popUpTo(NavRoutes.MainRoute.name)
                                        }
                                    }

                                    MainNavOption.MySort -> {
                                        navController.navigate(onUserPickedOption.name) {
                                            popUpTo(NavRoutes.MainRoute.name)
                                        }
                                    }

                                    MainNavOption.SettingsScreen -> {
                                        navController.navigate(onUserPickedOption.name) {
                                            popUpTo(NavRoutes.MainRoute.name)
                                        }
                                    }

                                    MainNavOption.AboutScreen -> {
                                        navController.navigate(onUserPickedOption.name) {
                                            popUpTo(NavRoutes.MainRoute.name)
                                        }
                                    }
                                }
                            }
                        },
                        gesturesEnabled = true
                    ) {
                        NavHost(
                            navController,
                            startDestination = if (onboardState) NavRoutes.MainRoute.name else NavRoutes.IntroRoute.name
                        ) {
                            introGraph(navController)
                            mainGraph(navController, drawerState)
                            subGraph(navController, drawerState)
                        }
                    }
                }
            }
        )
    }
}

enum class NavRoutes {
    IntroRoute,
    MainRoute,
    SubRoute
}

object DrawerParams {
    val drawerButtons = arrayListOf(
        AppDrawerItemInfo(
            MainNavOption.MyTable,
            R.string.drawer_home,
            R.drawable.ic_home,
            R.string.drawer_home_description
        ),
        AppDrawerItemInfo(
            MainNavOption.MyDataStructures,
            R.string.drawer_data_structures,
            R.drawable.ic_data_structure,
            R.string.drawer_data_structures_description
        ),
        AppDrawerItemInfo(
            MainNavOption.MySearch,
            R.string.drawer_search,
            R.drawable.ic_search,
            R.string.drawer_search_description
        ),
        AppDrawerItemInfo(
            MainNavOption.MySort,
            R.string.drawer_sort,
            R.drawable.ic_sort,
            R.string.drawer_sort_description
        ),
        AppDrawerItemInfo(
            MainNavOption.SettingsScreen,
            R.string.drawer_settings,
            R.drawable.ic_settings,
            R.string.drawer_settings_description
        ),
        AppDrawerItemInfo(
            MainNavOption.AboutScreen,
            R.string.drawer_about,
            R.drawable.ic_info,
            R.string.drawer_info_description
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MainActivityPreview() {
    MainCompose()
}