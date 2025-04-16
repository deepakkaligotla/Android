package `in`.kaligotla.allpermissions.presentation.main.permissions.camera

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import `in`.kaligotla.allpermissions.navigation.Screen
import `in`.kaligotla.allpermissions.presentation.rememberUserTheme
import `in`.kaligotla.allpermissions.proto.AppTheme
import `in`.kaligotla.allpermissions.ui.components.appbar.AppBar
import `in`.kaligotla.allpermissions.ui.theme.AllPermissionsTheme
import java.util.Objects

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MyCamera(
    userTheme: AppTheme,
    drawerState: DrawerState,
    viewModel: MyCameraViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val permissionState = rememberPermissionState("android.permission.CAMERA")
    val file = viewModel.createImageFile(context)
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "in.kaligotla.allpermissions.provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
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
                            text = "Camera",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
                Button(onClick = {
                    if (permissionState.status.isGranted) {
                        cameraLauncher.launch(uri)
                    } else {
                        permissionState.launchPermissionRequest()
                    }
                }) {
                    Text("Open Camera")
                }
                if (capturedImageUri.path?.isNotEmpty() == true) {
                    Image(
                        modifier = Modifier
                            .padding(16.dp, 8.dp),
                        painter = rememberImagePainter(capturedImageUri),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MyCameraScreenPreview() {
    AllPermissionsTheme(appTheme = rememberUserTheme()) {
        Screen.MyCameraScreen
    }
}