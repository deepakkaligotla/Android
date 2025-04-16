package `in`.kaligotla.datastructures.presentation.main.mySearch.myLinearSearch

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun MyLinearSearch(
    drawerState: DrawerState,
    viewModel: MyLinearSearchViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var context = LocalContext.current.applicationContext
    var searchPincode by rememberSaveable { mutableStateOf("") }
    val timerMap by rememberSaveable { mutableStateOf(mutableMapOf<String, Long>()) }
    var lazyList by rememberSaveable { mutableStateOf(emptyList<Location>().toMutableList()) }

    viewModel.setupObservers(lifecycleOwner)
    DatastructuresTheme {
        Scaffold(
            topBar = {
                AppBar(drawerState = drawerState)
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Linear Search",
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
                Text(text = "Enter Pincode to do linear search in the database")
                TextField(value = searchPincode, onValueChange = {
                    if (it.isDigitsOnly()) {
                        searchPincode = it
                    }
                }, label = { Text("Pincode") })

                Button(onClick = {
                    if (searchPincode.isNotBlank() && searchPincode.isNotEmpty() && searchPincode.isDigitsOnly()) {
                        lazyList.clear()
                        timerMap["BeforeLinearSearch"] = System.currentTimeMillis()
                        viewModel.linearSearchRec(searchPincode.toInt(), 0, lifecycleOwner, context)
                        timerMap["LinearSearch"] =
                            System.currentTimeMillis().minus(timerMap["BeforeLinearSearch"]!!)
                                .div(1000).mod(60)
                                .toLong()
                        lazyList = viewModel.items.toMutableList()
                    }
                }) {
                    Text(text = "Search")
                }

                if (timerMap.isNotEmpty()) Text(text = "Time taken\t: " + timerMap["LinearSearch"] + " secs")
                else Text(text = "Time taken\t: " + timerMap["LinearSearch"] + " secs")

                LazyVerticalGrid(
                    state = LazyGridState(0, 6),
                    columns = GridCells.Fixed(LocalConfiguration.current.screenWidthDp / 100),
                    modifier = Modifier.padding(all = 5.dp)
                ) {
                    if (lazyList.isNotEmpty()) {
                        items(lazyList) { location ->
                            LaunchItem(location = location, count = location.location_id)
                        }
                    } else {
                        items(viewModel.items) { location ->
                            LaunchItem(location = location, count = location.location_id)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun MyLinearSearchPreview() {
    MyLinearSearchScreen
}