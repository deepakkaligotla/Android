package `in`.kaligotla.datastructures.presentation.main.myDataStructures.myQueue

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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import `in`.kaligotla.datastructures.data.domain.Entry
import `in`.kaligotla.datastructures.navigation.Screen
import `in`.kaligotla.datastructures.presentation.commonComponents.DisplayListItem
import `in`.kaligotla.datastructures.ui.components.appbar.AppBar
import `in`.kaligotla.datastructures.ui.theme.DatastructuresTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyQueue(
    drawerState: DrawerState,
    viewModel: MyQueueViewModel = hiltViewModel()
) {
    var userInputIndex: String by rememberSaveable { mutableStateOf("") }
    var userInputValue: String by rememberSaveable { mutableStateOf("") }
    val timerMap = mutableMapOf<String, Long>()
    var lazyList by rememberSaveable { mutableStateOf(emptyList<Entry?>().toMutableList()) }

    DatastructuresTheme {
        Scaffold(
            topBar = {
                AppBar(drawerState = drawerState)
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Queue",
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
                        Column(
                            modifier = Modifier.padding(all = 2.dp)
                        ) {
                            Row {
                                Text(text = "Queue size: " + viewModel.items.toMutableList().size)
                            }
                            Row {
                                Column {
                                    Button(onClick = {
                                        lazyList.clear()
                                        timerMap["BeforeDeQueue"] = System.currentTimeMillis()
                                        viewModel.Deque()
                                        timerMap["DeQueue"] =
                                            System.currentTimeMillis()
                                                .minus(timerMap["BeforeDeQueue"]!!).div(1000)
                                                .mod(60).toLong()
                                        if (viewModel.items.isNotEmpty())
                                            lazyList = viewModel.items.toMutableList()
                                        else lazyList.clear()
                                    }) {
                                        Text(text = "DeQueue", fontSize = 12.sp)
                                    }
                                }
                                Column {
                                    Button(onClick = {
                                        lazyList.clear()
                                        timerMap["BeforeDeleteAll"] = System.currentTimeMillis()
                                        viewModel.delAll()
                                        timerMap["DeletetAll"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeDeleteAll"]!!)).div(1000).mod(60)
                                            .toLong()
                                        if (viewModel.items.isNotEmpty())
                                            lazyList = viewModel.items.toMutableList()
                                        else lazyList.clear()
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
                        Row {
                            Text(
                                text = "Time Taken in seconds: ",
                                textDecoration = TextDecoration.Underline
                            )
                        }
                        Row {
                            Column {
                                Text(text = "DeQueue\t: " + timerMap["DeQueue"])
                            }
                            Column {
                                Text(text = " || ")
                            }
                            Column {
                                Text(text = "DeleteAll\t: " + timerMap["DeletetAll"])
                            }
                        }
                    }
                }

                Row {
                    TextField(value = userInputIndex, onValueChange = {
                        if (it.isDigitsOnly()) {
                            userInputIndex = it
                        }
                    }, label = { Text("Value") }, modifier = Modifier
                        .size(120.dp, 70.dp)
                        .padding(all = 5.dp)
                    )

                    TextField(value = userInputValue, onValueChange = {
                        if (it.isDigitsOnly()) {
                            userInputValue = it
                        }
                    }, label = { Text("Value") }, modifier = Modifier
                        .size(120.dp, 70.dp)
                        .padding(all = 5.dp)
                    )
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
                                        lazyList.clear()
                                        timerMap["BeforeInsertAtFirst"] = System.currentTimeMillis()
                                        if (userInputValue.isDigitsOnly() && userInputValue.isNotEmpty() && userInputValue.isNotBlank()) {
                                            val temp = Entry(userInputIndex.toInt(), userInputValue)
                                            viewModel.addFirst(temp)
                                        }
                                        timerMap["InsertAtFirst"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeInsertAtFirst"]!!)).div(1000)
                                            .mod(60).toLong()
                                        lazyList = viewModel.items.toMutableList()
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
                                        lazyList.clear()
                                        timerMap["BeforeInsertAtLast"] = System.currentTimeMillis()
                                        if (userInputValue.isDigitsOnly() && userInputValue.isNotEmpty() && userInputValue.isNotBlank()) {
                                            val temp = Entry(userInputIndex.toInt(), userInputValue)
                                            viewModel.addLast(temp)
                                        }
                                        timerMap["InsertAtLast"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeInsertAtLast"]!!)).div(1000)
                                            .mod(60).toLong()
                                        lazyList = viewModel.items.toMutableList()
                                    }) {
                                        Text(text = "Insert at Last", fontSize = 12.sp)
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
                                    Text(text = "Delete at first\t: " + timerMap["DeletetAtFirst"])
                                }
                                Row {
                                    Button(onClick = {
                                        lazyList.clear()
                                        timerMap["BeforeDeleteAtFirst"] = System.currentTimeMillis()
                                        viewModel.delFirst()
                                        timerMap["DeletetAtFirst"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeDeleteAtFirst"]!!)).div(1000)
                                            .mod(60).toLong()
                                        if (viewModel.items.isNotEmpty())
                                            lazyList = viewModel.items.toMutableList()
                                        else lazyList.clear()
                                    }) {
                                        Text(text = "Delete at First", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                        Row {
                            Column {
                                Row {
                                    Text(text = "Delete at last\t: " + timerMap["DeletetAtLast"])
                                }
                                Row {
                                    Button(onClick = {
                                        lazyList.clear()
                                        timerMap["BeforeDeleteAtLast"] = System.currentTimeMillis()
                                        viewModel.delLast()
                                        timerMap["DeletetAtLast"] = (System.currentTimeMillis()
                                            .minus(timerMap["BeforeDeleteAtLast"]!!)).div(1000)
                                            .mod(60).toLong()
                                        if (viewModel.items.isNotEmpty())
                                            lazyList = viewModel.items.toMutableList()
                                        else lazyList.clear()
                                    }) {
                                        Text(text = "Delete at Last", fontSize = 12.sp)
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
                    if (lazyList.isNotEmpty()) {
                        items(lazyList) { item ->
                            DisplayListItem(item1 = "Index:" + item!!.key, item2 = item.value)
                        }
                    } else if (lazyList.isEmpty()) {
                        items(1) {
                            DisplayListItem(item1 = "Empty", item2 = "Queue")
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