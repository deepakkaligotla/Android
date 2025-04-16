package `in`.kaligotla.datastructures.presentation.main.myDataStructures

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import `in`.kaligotla.datastructures.navigation.Screen.*
import `in`.kaligotla.datastructures.ui.components.appbar.AppBar
import `in`.kaligotla.datastructures.ui.theme.DatastructuresTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MyDataStructures(
    drawerState: DrawerState,
    navController: NavHostController,
    viewModel: MyDataStructuresViewModel = hiltViewModel()
) {

    DatastructuresTheme {
        Scaffold(
            modifier = Modifier.testTag("myDataStructureTag"),
            topBar = {
                AppBar(drawerState = drawerState)
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Data Structures",
                        style = MaterialTheme.typography.headlineSmall
                    )
                })
            }) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Button(
                        modifier = Modifier.testTag("myVectorButton"),
                        onClick = {
                            navController.navigate(route = MyVectorScreen.route)
                        }) {
                        Text(text = "Vector")
                    }
                }
                Row {
                    Button(onClick = {
                        navController.navigate(route = MyLinkedListScreen.route)
                    }) {
                        Text(text = "Linked List")
                    }
                }
                Row {
                    Button(onClick = {
                        navController.navigate(route = MyQueueScreen.route)
                    }) {
                        Text(text = "Queue")
                    }
                }
                Row {
                    Button(onClick = {
                        navController.navigate(route = MyStackScreen.route)
                    }) {
                        Text(text = "Stack")
                    }
                }
                Row {
                    Button(onClick = {
                        navController.navigate(route = MyHashTableScreen.route)
                    }) {
                        Text(text = "Hash Table")
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun MyDataStructuresPreview() {
    MyDataStructuresScreen
}