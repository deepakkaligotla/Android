package `in`.kaligotla.datastructures.presentation.main.mySearch

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import `in`.kaligotla.datastructures.navigation.Screen.*
import `in`.kaligotla.datastructures.ui.components.appbar.AppBar
import `in`.kaligotla.datastructures.ui.theme.DatastructuresTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearch(
    drawerState: DrawerState,
    navController: NavHostController,
    viewModel: MySearchViewModel = hiltViewModel()
) {
    DatastructuresTheme {
        Scaffold(
            topBar = {
                AppBar(drawerState = drawerState)
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Search Algo",
                        style = MaterialTheme.typography.headlineSmall
                    )
                })
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Button(onClick = {
                        navController.navigate(route = MyBinarySearchScreen.route)
                    }) {
                        Text(text = "Binary")
                    }
                }
                Row {
                    Button(onClick = {
                        navController.navigate(route = MyBreadthFirstSearch.route)
                    }) {
                        Text(text = "Breadth First")
                    }
                }
                Row {
                    Button(onClick = {
                        navController.navigate(route = MyDepthFirstSearch.route)
                    }) {
                        Text(text = "Depth First")
                    }
                }
                Row {
                    Button(onClick = {
                        navController.navigate(route = MyExponentialSearchScreen.route)
                    }) {
                        Text(text = "Exponential")
                    }
                }
                Row {
                    Button(onClick = {
                        navController.navigate(route = MyInterpolationSearchScreen.route)
                    }) {
                        Text(text = "Interpolation")
                    }
                }
                Row {
                    Button(onClick = {
                        navController.navigate(route = MyJumpSearchScreen.route)
                    }) {
                        Text(text = "Jump")
                    }
                }
                Row {
                    Button(onClick = {
                        navController.navigate(route = MyLinearSearchScreen.route)
                    }) {
                        Text(text = "Linear")
                    }
                }
                Row {
                    Button(onClick = {
                        navController.navigate(route = MySequentialSearchScreen.route)
                    }) {
                        Text(text = "Sequential")
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun MySearchPreview() {
    MySearchScreen
}