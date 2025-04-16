package `in`.kaligotla.datastructures.presentation.main.myDataStructures.myHashTable

import android.os.Build
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import `in`.kaligotla.datastructures.data.domain.Entry
import `in`.kaligotla.datastructures.navigation.Screen.*
import `in`.kaligotla.datastructures.presentation.commonComponents.DisplayListItem
import `in`.kaligotla.datastructures.ui.components.appbar.AppBar
import `in`.kaligotla.datastructures.ui.theme.DatastructuresTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyHashTable(
    drawerState: DrawerState,
    viewModel: MyHashTableViewModel = hiltViewModel()
) {

    var lazyList by rememberSaveable { mutableStateOf(emptyList<Entry?>().toMutableList()) }
    var userInputSize: String by rememberSaveable { mutableStateOf("") }
    var userInputKey: String by rememberSaveable { mutableStateOf("") }
    var userInputValue: String by rememberSaveable { mutableStateOf("") }
    val contextForToast = LocalContext.current.applicationContext

    DatastructuresTheme {
        Scaffold(
            topBar = {
                AppBar(drawerState = drawerState)
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Hash Table",
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
                    TextField(value = userInputSize, onValueChange = {
                        if (it.isDigitsOnly()) {
                            userInputSize = it
                        }
                    }, label = { Text("Size") }, modifier = Modifier
                        .size(120.dp, 70.dp)
                        .padding(all = 5.dp)
                    )
                    Button(onClick = {
                        lazyList.clear()
                        if (userInputSize.isDigitsOnly())
                            viewModel.createHashTable(userInputSize.toInt())
                        lazyList = viewModel.items.toMutableList()
                    }) {
                        Text(text = "Create All", fontSize = 12.sp)
                    }
                }

                Row {
                    TextField(value = userInputKey, onValueChange = {
                        if (it.isDigitsOnly()) {
                            userInputKey = it
                        }
                    }, label = { Text("Key") }, modifier = Modifier
                        .size(120.dp, 70.dp)
                        .padding(all = 5.dp)
                    )
                    TextField(value = userInputValue, onValueChange = {
                        userInputValue = it
                    }, label = { Text("Value") }, modifier = Modifier
                        .size(120.dp, 70.dp)
                        .padding(all = 5.dp)
                    )
                    Button(onClick = {
                        lazyList.clear()
                        if (userInputSize.isDigitsOnly() && userInputSize.isNotEmpty() && userInputValue.isNotEmpty())
                            try {
                                viewModel.put(userInputKey.toInt(), userInputValue)
                            } catch (e: Exception) {
                                Toast.makeText(
                                    contextForToast,
                                    "Hash Table is full",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        lazyList = viewModel.items.toMutableList()
                    }) {
                        Text(text = "Put", fontSize = 12.sp)
                    }
                }

                Row {
                    TextField(value = userInputKey, onValueChange = {
                        if (it.isDigitsOnly()) {
                            userInputKey = it
                        }
                    }, label = { Text("Key") }, modifier = Modifier
                        .size(120.dp, 70.dp)
                        .padding(all = 5.dp)
                    )
                    Button(onClick = {
                        lazyList.clear()
                        if (userInputKey.isDigitsOnly())
                            viewModel.getValue(userInputKey.toInt())
                        lazyList = viewModel.items.toMutableList()
                    }) {
                        Text(text = "Find Value", fontSize = 12.sp)
                    }
                }

                LazyVerticalGrid(
                    state = LazyGridState(0, 6),
                    columns = GridCells.Fixed(LocalConfiguration.current.screenWidthDp / 100),
                    modifier = Modifier.padding(all = 5.dp),
                    content = {
                        if (lazyList.isNotEmpty()) {
                            items(lazyList) { item ->
                                if (item != null) DisplayListItem(
                                    item1 = "Key: " + item.key,
                                    item2 = item.value
                                )
                            }
                        } else {
                            items(viewModel.table.size) {
                                DisplayListItem(item1 = null, item2 = null)
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
fun MyHashTablePreview() {
    MyHashTableScreen
}