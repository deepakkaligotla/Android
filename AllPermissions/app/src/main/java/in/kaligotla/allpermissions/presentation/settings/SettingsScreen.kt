package `in`.kaligotla.allpermissions.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import `in`.kaligotla.allpermissions.ui.components.appbar.AppBar
import `in`.kaligotla.allpermissions.ui.previews.AllScreenPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(drawerState = drawerState)
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.headlineSmall
                )
            })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Settings")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@AllScreenPreview
@Composable
fun SettingsScreenPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    SettingsScreen(drawerState)
}