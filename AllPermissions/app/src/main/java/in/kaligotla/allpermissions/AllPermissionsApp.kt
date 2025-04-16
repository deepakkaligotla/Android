package `in`.kaligotla.allpermissions

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AllPermissionsApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}