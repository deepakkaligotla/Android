package `in`.kaligotla.allpermissions.presentation.main.permissions.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import `in`.kaligotla.allpermissions.R
import `in`.kaligotla.allpermissions.navigation.Screen
import `in`.kaligotla.allpermissions.presentation.rememberUserTheme
import `in`.kaligotla.allpermissions.proto.AppTheme
import `in`.kaligotla.allpermissions.ui.components.appbar.AppBar
import `in`.kaligotla.allpermissions.ui.theme.AllPermissionsTheme

private const val CHANNEL_ID = "CHANNEL ID"
private const val NOTIFICATION_ID = 666

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MyNotifications(
    userTheme: AppTheme,
    drawerState: DrawerState,
    viewModel: MyNotificationsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val permissionState = rememberPermissionState("android.permission.POST_NOTIFICATIONS")
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Channel Name",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }

    fun showNotification(context: Context, title: String, message: String) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_notification_icon)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    AllPermissionsTheme(appTheme = userTheme) {
        Scaffold(
            modifier = Modifier.testTag("myTableTag"),
            topBar = { AppBar(drawerState = drawerState) }
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
                            text = "Notifications",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }

                if (permissionState.status.isGranted) {
                    Button(onClick = {
                        showNotification(context, "New Message", "You have a new message!")
                    }) {
                        Text("Get all Notifications")
                    }

                } else {
                    Button(onClick = {
                        permissionState.launchPermissionRequest()
                    }) {
                        Text("Grant Notifications permission")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MyNotificationsScreenPreview() {
    AllPermissionsTheme(appTheme = rememberUserTheme()) {
        Screen.MyNotificationsScreen
    }
}