package `in`.kaligotla.broadcastreceiverdemo1

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import `in`.kaligotla.broadcastreceiverdemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private var myBroadcastReceiver: MyBroadcastReceiver = MyBroadcastReceiver()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.btnRegisterReceiver.setOnClickListener {
            var intentFilter = IntentFilter()
            intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
            intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            intentFilter.addAction(Intent.ACTION_CALL)
            registerReceiver(myBroadcastReceiver, intentFilter, 0)
        }

        activityMainBinding.btnUnRegisterReceiver.setOnClickListener {
            unregisterReceiver(myBroadcastReceiver)
        }
    }
}