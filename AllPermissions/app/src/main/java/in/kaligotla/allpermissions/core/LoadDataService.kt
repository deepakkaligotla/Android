package `in`.kaligotla.allpermissions.core

import android.app.Service
import android.content.Intent
import android.os.IBinder
import javax.inject.Inject

class LoadDataService @Inject constructor() : Service() {
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}