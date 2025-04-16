package `in`.kaligotla.list_and_grids.presentation.firstScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import `in`.kaligotla.list_and_grids.LaunchListQuery
import `in`.kaligotla.list_and_grids.core.apolloClient
import `in`.kaligotla.list_and_grids.navigation.Screen
import `in`.kaligotla.list_and_grids.ui.theme.List_and_GridsTheme
import `in`.kaligotla.list_and_grids.presentation.firstScreen.components.LaunchItem
import `in`.kaligotla.list_and_grids.presentation.firstScreen.components.LoadingItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FirstScreenUI(
    viewModel: FirstScreenViewModel = hiltViewModel(),
) {
    var cursor: String? by remember { mutableStateOf(null) }
    var response: ApolloResponse<LaunchListQuery.Data>? by remember { mutableStateOf(null) }
    var launchList by remember { mutableStateOf(emptyList<LaunchListQuery.Launch>()) }

    List_and_GridsTheme() {
        Scaffold(
            content = { padding ->
                Column(
                    modifier = Modifier.padding(end=10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LaunchedEffect(cursor) {
                        response = apolloClient.query(LaunchListQuery(Optional.present(cursor))).execute()
                        launchList = launchList + response?.data?.launches?.launches?.filterNotNull().orEmpty()
                    }
                    LazyHorizontalStaggeredGrid(
                        rows = StaggeredGridCells.Adaptive(minSize = 128.dp)
                    ) {
                        items(launchList) { launch ->
                            LaunchItem(launch = launch)
                        }

                        item {
                            if (response?.data?.launches?.hasMore == true) {
                                LoadingItem()
                                cursor = response?.data?.launches?.cursor
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun FirstScreenPreview() {
    Screen.FirstScreen
}