package `in`.kaligotla.allpermissions.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import `in`.kaligotla.allpermissions.presentation.main.permissions.physicalActivity.ActivityRecognitionService

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainCompose()
        }
        startService(Intent(this, ActivityRecognitionService::class.java))
    }
}