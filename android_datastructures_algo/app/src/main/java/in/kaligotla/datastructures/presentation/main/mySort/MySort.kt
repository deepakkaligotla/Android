package `in`.kaligotla.datastructures.presentation.main.mySort

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
fun MySort(
    drawerState: DrawerState,
    navController: NavHostController,
    viewModel: MySortViewModel = hiltViewModel()
) {

    DatastructuresTheme {
        Scaffold(
            topBar = {
                AppBar(drawerState = drawerState)
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Sort",
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
                        navController.navigate(route = MySelectionSortScreen.route)
                    }) {
                        Text(text = "Selection")
                    }
                }

                Row {
                    Button(onClick = {
                        navController.navigate(route = MyBubbleSortScreen.route)
                    }) {
                        Text(text = "Bubble")
                    }
                }

                Row {
                    Button(onClick = {
                        navController.navigate(route = MyHeapSortScreen.route)
                    }) {
                        Text(text = "Heap")
                    }
                }

                Row {
                    Button(onClick = {
                        navController.navigate(route = MyInsertionSortScreen.route)
                    }) {
                        Text(text = "Insertion")
                    }
                }

                Row {
                    Button(onClick = {
                        navController.navigate(route = MyMergeSortScreen.route)
                    }) {
                        Text(text = "Merge")
                    }
                }

                Row {
                    Button(onClick = {
                        navController.navigate(route = MyQuickSortScreen.route)
                    }) {
                        Text(text = "Quick")
                    }
                }

                Row {
                    Button(onClick = {
                        navController.navigate(route = MyShellSortScreen.route)
                    }) {
                        Text(text = "Shell")
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun MySortPreview() {
    MySortScreen
}