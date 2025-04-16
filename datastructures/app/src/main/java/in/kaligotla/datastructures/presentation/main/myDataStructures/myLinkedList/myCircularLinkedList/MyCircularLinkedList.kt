package `in`.kaligotla.datastructures.presentation.main.myDataStructures.myLinkedList.myCircularLinkedList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import `in`.kaligotla.datastructures.data.domain.model.entities.Location
import `in`.kaligotla.datastructures.navigation.Screen
import `in`.kaligotla.datastructures.presentation.commonComponents.LaunchItem
import `in`.kaligotla.datastructures.presentation.commonComponents.LoadingItem
import `in`.kaligotla.datastructures.ui.components.appbar.AppBar
import `in`.kaligotla.datastructures.ui.theme.DatastructuresTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCircularLinkedList(
    drawerState: DrawerState,
    viewModel: MyCircularLinkedListViewModel = hiltViewModel()
) {
    val cursor: String? by rememberSaveable { mutableStateOf(null) }
    var userInputPosition: String by rememberSaveable { mutableStateOf("") }
    var userInputSize: String by rememberSaveable { mutableStateOf("") }
    val userInputValue: Location by rememberSaveable {
        mutableStateOf(
            Location(
                0,
                "XXXX",
                0,
                "AREA-XXX",
                "CITY-XXX",
                "DISTRICT-XXXX",
                "STATE-XXX"
            )
        )
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    val timerMap = mutableMapOf<String, Long>()


    timerMap["BeforeCreateAllNodes"] = System.currentTimeMillis()
    viewModel.setupObservers(lifecycleOwner)
    timerMap["CreateAllNodes"] =
        System.currentTimeMillis().minus(timerMap["BeforeCreateAllNodes"]!!).div(1000).mod(60)
            .toLong()

    DatastructuresTheme {
        Scaffold(
            topBar = {
                AppBar(drawerState = drawerState)
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Circular LL",
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

                Column(
                    modifier = Modifier.padding(all = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(all = 2.dp)
                    ) {
                        TextField(value = userInputValue.toString(),
                            onValueChange = {},
                            modifier = Modifier
                                .size(350.dp, 100.dp)
                                .padding(all = 5.dp),
                            enabled = false,
                            label = { Text("Value") })
                    }
                    Row(
                        modifier = Modifier.padding(all = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(all = 2.dp)
                        ) {
                            TextField(value = userInputSize, onValueChange = {
                                userInputSize = it
                                if (userInputSize.isDigitsOnly() && userInputSize.isNotEmpty() && userInputSize.isNotBlank()) viewModel.createNodeStructureOfExistingData(
                                    userInputSize.toInt()
                                )
                            }, label = { Text("Size") }, modifier = Modifier
                                .size(130.dp, 60.dp)
                                .padding(all = 2.dp)
                            )
                        }
                        Column(
                            modifier = Modifier.padding(all = 2.dp)
                        ) {
                            Row {
                                Text(text = "Linked List size: " + viewModel.items.toMutableList().size)
                            }
                            Row {
                                Column {
                                    Button(onClick = {
                                        timerMap["BeforeCreateAllNodes"] =
                                            System.currentTimeMillis()
                                        viewModel.setupObservers(lifecycleOwner)
                                        viewModel.createNodeStructureOfExistingData(userInputSize.toInt())
                                        timerMap["CreateAllNodes"] =
                                            System.currentTimeMillis()
                                                .minus(timerMap["BeforeCreateAllNodes"]!!).div(1000)
                                                .mod(60).toLong()
                                    }) {
                                        Text(text = "Create All", fontSize = 12.sp)
                                    }
                                }
                                Column {
                                    Button(onClick = {
                                        timerMap["BeforeDeleteAll"] = System.currentTimeMillis()
                                        viewModel.deleteAllJobsInCLL()
                                        timerMap["DeletetAll"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeDeleteAll"]!!)).div(1000).mod(60)
                                            .toLong()
                                    }) {
                                        Text(text = "Delete All", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.padding(all = 2.dp)
                ) {
                    Column {
                        TextField(value = userInputPosition, onValueChange = {
                            userInputPosition = it
                        }, label = { Text("Position") }, modifier = Modifier
                            .size(100.dp, 60.dp)
                            .padding(all = 2.dp)
                        )
                    }
                    Column {
                        Row {
                            Text(
                                text = "Time Taken in seconds: ",
                                textDecoration = TextDecoration.Underline
                            )
                        }
                        Row {
                            Column {
                                Text(text = "CreateAll\t: " + timerMap["CreateAllNodes"])
                            }
                            Column {
                                Text(text = " || ")
                            }
                            Column {
                                Text(text = "DeletetAll\t: " + timerMap["DeletetAll"])
                            }
                        }
                    }
                }

                Row {
                    Column(
                        modifier = Modifier.padding(all = 5.dp)
                    ) {
                        Row {
                            Column {
                                Row {
                                    Text(text = "Insert at First\t: " + timerMap["InsertAtFirst"])
                                }
                                Row {
                                    Button(onClick = {
                                        timerMap["BeforeInsertAtFirst"] = System.currentTimeMillis()
                                        viewModel.addJobAtFirst(userInputValue)
                                        timerMap["InsertAtFirst"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeInsertAtFirst"]!!)).div(1000)
                                            .mod(60).toLong()
                                    }) {
                                        Text(text = "Insert at First", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                        Row {
                            Column {
                                Row {
                                    Text(text = "Insert at last\t: " + timerMap["InsertAtLast"])
                                }
                                Row {
                                    Button(onClick = {
                                        timerMap["BeforeInsertAtLast"] = System.currentTimeMillis()
                                        viewModel.addJobAtLast(userInputValue)
                                        timerMap["InsertAtLast"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeInsertAtLast"]!!)).div(1000)
                                            .mod(60).toLong()
                                    }) {
                                        Text(text = "Insert at Last", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                        Row {
                            Column {
                                Row {
                                    Text(text = "Insert at position\t: " + timerMap["InsertAtPosition"])
                                }
                                Row {
                                    Button(onClick = {
                                        timerMap["BeforeInsertAtPosition"] =
                                            System.currentTimeMillis()
                                        if (userInputPosition.isDigitsOnly() && userInputPosition.isNotBlank() && userInputPosition.isNotEmpty()) viewModel.addJobAtPosition(
                                            userInputValue,
                                            userInputPosition.toInt()
                                        )
                                        timerMap["InsertAtPosition"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeInsertAtPosition"]!!)).div(1000)
                                            .mod(60).toLong()
                                    }) {
                                        Text(text = "Insert at position", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }

                    Column(
                        modifier = Modifier.padding(all = 5.dp)
                    ) {
                        Row {
                            Column {
                                Row {
                                    Text(text = "Insert at first\t: " + timerMap["DeletetAtFirst"])
                                }
                                Row {
                                    Button(onClick = {
                                        timerMap["BeforeDeleteAtFirst"] = System.currentTimeMillis()
                                        viewModel.deleteJobAtFirst()
                                        timerMap["DeletetAtFirst"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeDeleteAtFirst"]!!)).div(1000)
                                            .mod(60).toLong()
                                    }) {
                                        Text(text = "Delete at First", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                        Row {
                            Column {
                                Row {
                                    Text(text = "Insert at last\t: " + timerMap["DeletetAtLast"])
                                }
                                Row {
                                    Button(onClick = {
                                        timerMap["BeforeDeleteAtLast"] = System.currentTimeMillis()
                                        viewModel.deleteJobAtLast()
                                        timerMap["DeletetAtLast"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeDeleteAtLast"]!!)).div(1000)
                                            .mod(60).toLong()
                                    }) {
                                        Text(text = "Delete at Last", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                        Row {
                            Column {
                                Row {
                                    Text(text = "Insert at position\t: " + timerMap["DeletetAtPosition"])
                                }
                                Row {
                                    Button(onClick = {
                                        timerMap["BeforeDeleteAtPosition"] =
                                            System.currentTimeMillis()
                                        if (userInputPosition.isDigitsOnly() && userInputPosition.isNotEmpty() && userInputPosition.isNotBlank()) viewModel.deleteJobAtPosition(
                                            userInputPosition.toInt()
                                        )
                                        timerMap["DeletetAtPosition"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeDeleteAtPosition"]!!)).div(1000)
                                            .mod(60).toLong()
                                    }) {
                                        Text(text = "Delete at position", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                }

                LazyVerticalGrid(
                    state = LazyGridState(0, 6),
                    columns = GridCells.Fixed(LocalConfiguration.current.screenWidthDp / 100),
                    modifier = Modifier.padding(all = 5.dp)
                ) {
                    items(viewModel.items.toMutableList()) { location ->
                        LaunchItem(location = location, count = location.location_id)
                    }
                    item {
                        if (viewModel.locationsList.toMutableList().size != cursor?.length || viewModel.locationsList.toMutableList().size != cursor?.length) {
                            LoadingItem()
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun MySinglyLinkedListPreview() {
    Screen.MySinglyLinkedListScreen
}