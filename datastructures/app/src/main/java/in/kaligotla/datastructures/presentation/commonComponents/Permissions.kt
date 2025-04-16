package `in`.kaligotla.datastructures.presentation.commonComponents

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.BatteryManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Permissions() {
    val context = LocalContext.current
    val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    val batLevel by remember { mutableStateOf(bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)) }
    val permissionsList: Array<String> = arrayOf(
        //Body Sensors
        Manifest.permission.BODY_SENSORS,

        //Calendar
        Manifest.permission.READ_CALENDAR,
        Manifest.permission.WRITE_CALENDAR,

        //Call logs
        Manifest.permission.READ_CALL_LOG,
        Manifest.permission.WRITE_CALL_LOG,
        Manifest.permission.PROCESS_OUTGOING_CALLS,

        //Camera
        Manifest.permission.CAMERA,

        //Contacts
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.GET_ACCOUNTS,

        //Location
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,

        //Microphone
        Manifest.permission.RECORD_AUDIO,

        //Music and audio
        Manifest.permission.READ_MEDIA_AUDIO,

        //Nearby devices
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.BLUETOOTH_ADVERTISE,
        Manifest.permission.NEARBY_WIFI_DEVICES,
        Manifest.permission.UWB_RANGING,

        //Notifications
        Manifest.permission.POST_NOTIFICATIONS,

        //Phone
        Manifest.permission.READ_PHONE_NUMBERS,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.ANSWER_PHONE_CALLS,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.ACCEPT_HANDOVER,
        Manifest.permission.USE_SIP,

        //Photos and videos
        Manifest.permission.READ_MEDIA_IMAGES,
        Manifest.permission.READ_MEDIA_VIDEO,
        Manifest.permission.ACCESS_MEDIA_LOCATION,

        //Physical activity
        Manifest.permission.ACTIVITY_RECOGNITION,

        //SMS
        Manifest.permission.RECEIVE_MMS,
        Manifest.permission.RECEIVE_SMS,
        Manifest.permission.SEND_SMS,
        Manifest.permission.RECEIVE_WAP_PUSH,

        /*Common just add to manifest no require of user permission for below*/
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_NOTIFICATION_POLICY,
        Manifest.permission.BROADCAST_STICKY,
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN,
        Manifest.permission.CALL_COMPANION_APP,
        Manifest.permission.CHANGE_WIFI_STATE,
        Manifest.permission.CHANGE_WIFI_MULTICAST_STATE,
        Manifest.permission.CHANGE_NETWORK_STATE,
        Manifest.permission.DELIVER_COMPANION_MESSAGES,
        Manifest.permission.DISABLE_KEYGUARD,
        Manifest.permission.EXPAND_STATUS_BAR,
        Manifest.permission.FOREGROUND_SERVICE,
        Manifest.permission.GET_PACKAGE_SIZE,
        Manifest.permission.GET_TASKS,
        Manifest.permission.HIDE_OVERLAY_WINDOWS,
        Manifest.permission.HIGH_SAMPLING_RATE_SENSORS,
        Manifest.permission.INTERNET,
        Manifest.permission.KILL_BACKGROUND_PROCESSES,
        Manifest.permission.MANAGE_OWN_CALLS,
        Manifest.permission.MODIFY_AUDIO_SETTINGS,
        Manifest.permission.NFC_TRANSACTION_EVENT,
        Manifest.permission.NFC,
        Manifest.permission.NFC_PREFERRED_PAYMENT_INFO,
        Manifest.permission.PERSISTENT_ACTIVITY,
        Manifest.permission.RESTART_PACKAGES,
        Manifest.permission.READ_BASIC_PHONE_STATE,
        Manifest.permission.REQUEST_PASSWORD_COMPLEXITY,
        Manifest.permission.REQUEST_OBSERVE_COMPANION_DEVICE_PRESENCE,
        Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
        Manifest.permission.REQUEST_DELETE_PACKAGES,
        Manifest.permission.REQUEST_COMPANION_USE_DATA_IN_BACKGROUND,
        Manifest.permission.REQUEST_COMPANION_START_FOREGROUND_SERVICES_FROM_BACKGROUND,
        Manifest.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND,
        Manifest.permission.REQUEST_COMPANION_PROFILE_WATCH,
        Manifest.permission.REORDER_TASKS,
        Manifest.permission.RECEIVE_BOOT_COMPLETED,
        Manifest.permission.READ_SYNC_STATS,
        Manifest.permission.READ_SYNC_SETTINGS,
        Manifest.permission.SET_WALLPAPER_HINTS,
        Manifest.permission.SET_WALLPAPER,
        Manifest.permission.SCHEDULE_EXACT_ALARM,
        Manifest.permission.TRANSMIT_IR,
        Manifest.permission.USE_FINGERPRINT,
        Manifest.permission.USE_FULL_SCREEN_INTENT,
        Manifest.permission.USE_EXACT_ALARM,
        Manifest.permission.UPDATE_PACKAGES_WITHOUT_USER_ACTION,
        Manifest.permission.USE_BIOMETRIC,
        Manifest.permission.VIBRATE,
        Manifest.permission.WRITE_SYNC_SETTINGS,
        Manifest.permission.WAKE_LOCK,

        /*Problem with below*/
        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
        Manifest.permission.BODY_SENSORS_BACKGROUND,
        Manifest.permission.MANAGE_EXTERNAL_STORAGE,
        Manifest.permission.REQUEST_INSTALL_PACKAGES,
        Manifest.permission.REQUEST_COMPANION_PROFILE_AUTOMOTIVE_PROJECTION,
        Manifest.permission.READ_VOICEMAIL,
        Manifest.permission.READ_ASSISTANT_APP_SEARCH_DATA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_HOME_APP_SEARCH_DATA,
        Manifest.permission.SUBSCRIBE_TO_KEYGUARD_LOCKED_STATE,
        Manifest.permission.SYSTEM_ALERT_WINDOW,
        Manifest.permission.WRITE_VOICEMAIL,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
//        Log.e("",""+permissionsMap)
        if (areGranted) {
            //
        } else {
            //
        }
    }

    fun checkAndRequestPermission(
        context: Context,
        allPermissionList: Array<String>,
        launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>
    ) {
        if (allPermissionList.all {
                Log.e("Granted", it)
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }) {

        } else {
            launcher.launch(allPermissionList)
        }
    }

    Row {
        Column {
            Button(
                onClick = {
                    Log.e("battery", "battery level - $batLevel")
                    checkAndRequestPermission(context, permissionsList, requestPermissionLauncher)
                }) {
                Text(text = "Permissions")
            }
        }
    }
}