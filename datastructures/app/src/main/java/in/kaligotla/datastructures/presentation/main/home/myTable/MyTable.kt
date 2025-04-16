package `in`.kaligotla.datastructures.presentation.main.home.myTable

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import `in`.kaligotla.datastructures.data.domain.model.entities.Location
import `in`.kaligotla.datastructures.navigation.Screen.*
import `in`.kaligotla.datastructures.presentation.commonComponents.LaunchItem
import `in`.kaligotla.datastructures.ui.components.appbar.AppBar
import `in`.kaligotla.datastructures.ui.theme.DatastructuresTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTable(
    drawerState: DrawerState,
    viewModel: MyTableViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var searchPincode by rememberSaveable { mutableStateOf("") }
    var searchID by rememberSaveable { mutableStateOf("") }
    var lazyList by rememberSaveable { mutableStateOf(emptyList<Location>().toMutableList()) }

    viewModel.setupObservers(lifecycleOwner)
    lazyList = if (searchPincode.isNotEmpty() || searchID.isNotEmpty()) {
        lazyList.clear()
        viewModel.locationsSearchList.toMutableList()
    } else {
        lazyList.clear()
        viewModel.locationsList.toMutableList()
    }

    DatastructuresTheme {
        Scaffold(
            modifier = Modifier.testTag("myTableTag"),
            topBar = {
                AppBar(drawerState = drawerState)
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Home",
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
                Column(modifier = Modifier.padding(top = 5.dp)) {
                    Row {
                        Text(
                            text = "Locations Table",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    Row {
                        TextField(
                            modifier = Modifier
                                .size(150.dp, 80.dp)
                                .padding(all = 5.dp)
                                .testTag("myTablePincode")
                                .semantics { contentDescription = "" },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            value = searchPincode,
                            onValueChange = {
                                if (it.isDigitsOnly()) {
                                    lazyList.clear()
                                    searchPincode = it
                                    viewModel.searchPincode(searchPincode, lifecycleOwner)
                                    lazyList = viewModel.locationsSearchList.toMutableList()
                                }
                            }, label = { Text("Pincode") })

                        TextField(
                            modifier = Modifier
                                .size(150.dp, 80.dp)
                                .padding(all = 5.dp)
                                .testTag("myTableID")
                                .semantics { contentDescription = "" },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            value = searchID,
                            onValueChange = {
                                if (it.isDigitsOnly()) {
                                    lazyList.clear()
                                    searchID = it
                                    viewModel.searchID(searchID, lifecycleOwner)
                                    lazyList = viewModel.locationsSearchList.toMutableList()
                                }
                            }, label = { Text("ID") })
                    }
                    Row {
                        Text(text = "Total Records - " + lazyList.size + " of (" + viewModel.locationsList.size + ")")
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(LocalConfiguration.current.screenWidthDp / 100),
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("myTableLazyGrid"),
                    contentPadding = PaddingValues(0.dp),
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    horizontalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    if (lazyList.isNotEmpty()) {
                        items(lazyList) { location ->
                            LaunchItem(location = location, count = location.location_id)
                        }
                    } else {
                        items(viewModel.locationsList.toMutableList()) { location ->
                            LaunchItem(location = location, count = location.location_id)
                        }
                    }
                }
            }
        }
    }
}

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}

@Preview
@Composable
fun AuthScreenPreview() {
    MyTableScreen
}