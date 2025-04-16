package `in`.kaligotla.datastructures.presentation.main.myDataStructures.myLinkedList.mySinglyLinkedList

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import `in`.kaligotla.datastructures.navigation.Screen.*
import `in`.kaligotla.datastructures.presentation.commonComponents.LaunchItem
import `in`.kaligotla.datastructures.ui.components.appbar.AppBar
import `in`.kaligotla.datastructures.ui.theme.DatastructuresTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySinglyLinkedList(
    drawerState: DrawerState,
    viewModel: MySinglyLinkedListViewModel = hiltViewModel()
) {
    var userInputPosition: String by rememberSaveable { mutableStateOf("") }
    var list by rememberSaveable { mutableStateOf(emptyList<Location>().toMutableList()) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val timerMap = mutableMapOf<String, Long>()


    timerMap["BeforeCreateAllNodes"] = System.currentTimeMillis()
    viewModel.setupObservers(lifecycleOwner)
    viewModel.createNodeStructureOfExistingData()
    timerMap["CreateAllNodes"] =
        System.currentTimeMillis().minus(timerMap["BeforeCreateAllNodes"]!!).div(1000).mod(60)
            .toLong()

    DatastructuresTheme {
        Scaffold(
            topBar = {
                AppBar(drawerState = drawerState)
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Singly LL",
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

                Column {
                    Row {
                        TextField(value = "Location(0, XXXX, 0, AREA-XXX, CITY-XXX, DISTRICT-XXXX, STATE-XXX)",
                            onValueChange = {},
                            modifier = Modifier
                                .size(350.dp, 100.dp)
                                .padding(all = 5.dp),
                            enabled = false,
                            label = { Text("Value") })
                    }
                    Row(modifier = Modifier.padding(all = 5.dp)) {
                        Column {
                            TextField(value = userInputPosition, onValueChange = {
                                if (it.isDigitsOnly()) {
                                    userInputPosition = it
                                }
                            }, label = { Text("Position") }, modifier = Modifier
                                .size(120.dp, 70.dp)
                                .padding(all = 5.dp)
                            )
                        }
                        Column {
                            Row {
                                Text(text = "Linked List size: " + viewModel.items.toMutableList().size)
                            }
                            Row {
                                Column {
                                    Button(onClick = {
                                        list.clear()
                                        timerMap["BeforeCreateAllNodes"] =
                                            System.currentTimeMillis()
                                        viewModel.setupObservers(lifecycleOwner)
                                        viewModel.createNodeStructureOfExistingData()
                                        timerMap["CreateAllNodes"] =
                                            System.currentTimeMillis()
                                                .minus(timerMap["BeforeCreateAllNodes"]!!).div(1000)
                                                .mod(60).toLong()
                                        list = viewModel.items
                                    }) {
                                        Text(text = "Create All", fontSize = 12.sp)
                                    }
                                }
                                Column {
                                    Button(onClick = {
                                        list.clear()
                                        timerMap["BeforeDeleteAll"] = System.currentTimeMillis()
                                        viewModel.delAllNodes()
                                        timerMap["DeletetAll"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeDeleteAll"]!!)).div(1000).mod(60)
                                            .toLong()
                                        list = viewModel.items
                                    }) {
                                        Text(text = "Delete All", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                }
                Column {
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
                    Column {
                        Text(text = "Insert - ")
                    }
                    Column {
                        Text(text = "AtFirst\t: " + timerMap["InsertAtFirst"])
                    }
                    Column {
                        Text(text = " || ")
                    }
                    Column {
                        Text(text = "AtLast\t: " + timerMap["InsertAtLast"])
                    }
                    Column {
                        Text(text = " || ")
                    }
                    Column {
                        Text(text = "AtPosition\t: " + timerMap["InsertAtPosition"])
                    }
                }
                Row {
                    Column {
                        Text(text = "Delete - ")
                    }
                    Column {
                        Text(text = "AtFirst\t: " + timerMap["DeletetAtFirst"])
                    }
                    Column {
                        Text(text = " || ")
                    }
                    Column {
                        Text(text = "AtLast\t: " + timerMap["DeletetAtLast"])
                    }
                    Column {
                        Text(text = " || ")
                    }
                    Column {
                        Text(text = "AtPosition\t: " + timerMap["DeletetAtPosition"])
                    }
                }
                Row {
                    Button(onClick = {
                        Log.e("before", "" + list)
                        timerMap["BeforeInsertAtFirst"] = System.currentTimeMillis()
                        viewModel.insertAtFirst(
                            Location(
                                viewModel.items.toMutableList().size + 1,
                                "XXXX",
                                0,
                                "AREA-XXX",
                                "CITY-XXX",
                                "DISTRICT-XXXX",
                                "STATE-XXX"
                            )
                        )
                        timerMap["InsertAtFirst"] = (System.currentTimeMillis()
                            .minus(timerMap["BeforeInsertAtFirst"]!!)).div(1000).mod(60).toLong()
                        list.clear()
                        list = viewModel.items.toMutableList()
                    }) {
                        Text(text = "Insert at first", fontSize = 12.sp)
                    }

                    Button(onClick = {
                        timerMap["BeforeInsertAtLast"] = System.currentTimeMillis()
                        viewModel.insertAtLast(
                            Location(
                                viewModel.items.toMutableList().size + 1,
                                "XXXX",
                                0,
                                "AREA-XXX",
                                "CITY-XXX",
                                "DISTRICT-XXXX",
                                "STATE-XXX"
                            )
                        )
                        timerMap["InsertAtLast"] = (System.currentTimeMillis()
                            .minus(timerMap["BeforeInsertAtLast"]!!)).div(1000).mod(60).toLong()
                        list.clear()
                        list = viewModel.items.toMutableList()
                    }) {
                        Text(text = "Insert at last", fontSize = 12.sp)
                    }

                    Button(onClick = {
                        timerMap["BeforeInsertAtPosition"] = System.currentTimeMillis()
                        if (userInputPosition.isDigitsOnly() && userInputPosition.isNotEmpty() && userInputPosition.isNotBlank()) viewModel.insertAtPosition(
                            Location(
                                viewModel.items.toMutableList().size + 1,
                                "XXXX",
                                0,
                                "AREA-XXX",
                                "CITY-XXX",
                                "DISTRICT-XXXX",
                                "STATE-XXX"
                            ),
                            userInputPosition.toInt()
                        )
                        timerMap["InsertAtPosition"] = (System.currentTimeMillis()
                            .minus(timerMap["BeforeInsertAtPosition"]!!)).div(1000).mod(60).toLong()
                        list.clear()
                        list = viewModel.items.toMutableList()
                    }) {
                        Text(text = "Insert at position", fontSize = 12.sp)
                    }
                }

                Row {
                    Button(onClick = {
                        list.clear()
                        timerMap["BeforeDeleteAtFirst"] = System.currentTimeMillis()
                        viewModel.deleteFirstNode()
                        timerMap["DeletetAtFirst"] = (System.currentTimeMillis()
                            .minus(timerMap["BeforeDeleteAtFirst"]!!)).div(1000).mod(60).toLong()
                        list.clear()
                        list = viewModel.items.toMutableList()
                    }) {
                        Text(text = "Delete at first", fontSize = 12.sp)
                    }
                    Button(onClick = {
                        list.clear()
                        timerMap["BeforeDeleteAtLast"] = System.currentTimeMillis()
                        viewModel.deleteLastNode()
                        timerMap["DeletetAtLast"] = (System.currentTimeMillis()
                            .minus(timerMap["BeforeDeleteAtLast"]!!)).div(1000).mod(60).toLong()
                        list.clear()
                        list = viewModel.items.toMutableList()
                    }) {
                        Text(text = "Delete at last", fontSize = 12.sp)
                    }
                    Button(onClick = {
                        list.clear()
                        timerMap["BeforeDeleteAtPosition"] = System.currentTimeMillis()
                        if (userInputPosition.isDigitsOnly() && userInputPosition.isNotEmpty() && userInputPosition.isNotBlank()) viewModel.deleteNodeAtPos(
                            userInputPosition.toInt()
                        )
                        timerMap["DeletetAtPosition"] = (System.currentTimeMillis()
                            .minus(timerMap["BeforeDeleteAtPosition"]!!)).div(1000).mod(60).toLong()
                        list.clear()
                        list = viewModel.items.toMutableList()
                    }) {
                        Text(text = "Delete at position", fontSize = 12.sp)
                    }
                }

                LazyVerticalGrid(
                    state = LazyGridState(0, 6),
                    columns = GridCells.Fixed(LocalConfiguration.current.screenWidthDp / 100),
                    modifier = Modifier.padding(all = 5.dp),
                    content = {
                        if (list.isNotEmpty()) {
                            items(items = list,
                                key = { location: Location ->
                                    location.location_id
                                }) { location: Location ->
                                LaunchItem(location = location, count = location.location_id)
                            }
                        } else {
                            items(items = viewModel.items,
                                key = { location: Location ->
                                    location.location_id
                                }) { location: Location ->
                                LaunchItem(location = location, count = location.location_id)
                            }
                        }
                    }

                )
            }
        }
    }
}


@Preview
@Composable
fun MySinglyLinkedListPreview() {
    MySinglyLinkedListScreen
}