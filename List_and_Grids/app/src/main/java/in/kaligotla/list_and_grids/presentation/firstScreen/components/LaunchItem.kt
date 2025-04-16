package `in`.kaligotla.list_and_grids.presentation.firstScreen.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import `in`.kaligotla.list_and_grids.LaunchListQuery
import `in`.kaligotla.list_and_grids.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchItem(launch: LaunchListQuery.Launch) {
    ListItem(
        headlineText = {
            // Mission name
            Text(text = launch.mission?.name ?: "Not Available")
        },
        supportingText = {
            // Site
            Text(text = launch.site ?: "Not Available")
        },
        leadingContent = {
            // Mission patch
            AsyncImage(
                modifier = Modifier.size(68.dp, 68.dp),
                model = launch.mission?.missionPatch,
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder),
                contentDescription = "Mission patch"
            )
        }
    )
}