package `in`.kaligotla.datastructures.presentation.main.myDataStructures.myVector

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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun MyVector(
    drawerState: DrawerState,
    viewModel: MyVectorViewModel = hiltViewModel()
) {
    var arraySize by rememberSaveable { mutableStateOf("") }
    var userInputIndex by rememberSaveable { mutableStateOf("") }
    var userInputValue by rememberSaveable { mutableStateOf("") }
    var arrayLazyList by rememberSaveable { mutableStateOf(emptyList<Entry?>().toMutableList()) }
    var vectorLazyList by rememberSaveable { mutableStateOf(emptyList<Entry?>().toMutableList()) }
    val contextForToast = LocalContext.current.applicationContext

    DatastructuresTheme {
        Scaffold(
            modifier = Modifier.testTag("myVectorTag"),
            topBar = {
                AppBar(drawerState = drawerState)
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Vector",
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
                    TextField(
                        modifier = Modifier
                            .size(120.dp, 70.dp)
                            .padding(all = 5.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = arraySize,
                        onValueChange = {
                            if (it.isDigitsOnly()) {
                                arraySize = it
                            }
                        }, label = { Text("Array Size") })

                    Button(onClick = {
                        if (arraySize.isNotBlank() && arraySize.isNotEmpty() && arraySize.isDigitsOnly()) {
                            viewModel.createArray(arraySize.toInt())
                        }
                    }) {
                        Text(text = "Create Array")
                    }
                }

                Row {
                    TextField(
                        modifier = Modifier
                            .size(150.dp, 70.dp)
                            .padding(all = 5.dp)
                            .semantics { contentDescription = "" },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = userInputIndex,
                        onValueChange = {
                            if (it.isDigitsOnly()) {
                                userInputIndex = it
                            }
                        }, label = { Text("Index") })

                    TextField(
                        modifier = Modifier
                            .size(150.dp, 70.dp)
                            .padding(all = 5.dp)
                            .semantics { contentDescription = "" },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        value = userInputValue,
                        onValueChange = {
                            userInputValue = it
                        },
                        label = { Text("Value") })
                }

                Button(onClick = {
                    var userInput = Entry(userInputIndex.toInt(), userInputValue)
                    if (userInput.key != null && userInput.value != null) {
                        arrayLazyList.clear()
                        userInput.key = userInputIndex.toInt()
                        userInput.value = userInputValue
                        try {
                            viewModel.addToArray(userInput)
                        } catch (e: Exception) {
                            Toast.makeText(
                                contextForToast,
                                "Array table if full",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        arrayLazyList = viewModel.arrayItems.toMutableList()
                    }
                }) {
                    Text(text = "Add to Array")
                }

                LazyVerticalGrid(
                    state = LazyGridState(0, 6),
                    columns = GridCells.Fixed(LocalConfiguration.current.screenWidthDp / 100),
                    modifier = Modifier.padding(all = 5.dp),
                    content = {
                        if (arrayLazyList.isNotEmpty()) {
                            items(arrayLazyList) { item ->
                                if (item != null) {
                                    DisplayListItem(
                                        item1 = "Index: " + item.key,
                                        item2 = item.value
                                    )
                                } else {
                                    DisplayListItem(item1 = null, item2 = null)
                                }
                            }
                        } else {
                            items(viewModel.arrayItems) {
                                DisplayListItem(item1 = null, item2 = null)
                            }
                        }
                    }
                )

                Button(onClick = {
                    var userInput = Entry(userInputIndex.toInt(), userInputValue)
                    if (userInput.key != null && userInput.value != null) {
                        vectorLazyList.clear()
                        userInput.key = userInputIndex.toInt()
                        userInput.value = userInputValue
                        viewModel.addToVector(userInput)
                        vectorLazyList = viewModel.vectorItems.toMutableList()
                    }
                }) {
                    Text(text = "Add to Vector")
                }

                LazyVerticalGrid(
                    state = LazyGridState(0, 6),
                    columns = GridCells.Fixed(LocalConfiguration.current.screenWidthDp / 100),
                    modifier = Modifier.padding(all = 5.dp),
                    content = {
                        if (vectorLazyList.isNotEmpty()) {
                            items(vectorLazyList) { item ->
                                if (item != null) {
                                    DisplayListItem(
                                        item1 = "Index: " + item.key,
                                        item2 = item.value
                                    )
                                } else {
                                    DisplayListItem(item1 = null, item2 = null)
                                }
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
fun MyVectorPreview() {
    MyVectorScreen
}