package `in`.kaligotla.broadcastreceiverdemo1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null || intent.action == null) return
        val action: String = intent.action!!

        when (action) {
            Intent.ACTION_BATTERY_LOW -> Toast.makeText(context, "Battery low!", Toast.LENGTH_LONG)
                .show()
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> Toast.makeText(context, "Airplane Mode Changed", Toast.LENGTH_SHORT)
                .show()

            Intent.ACTION_SCREEN_ON -> Toast.makeText(
                context,
                "Screen turned ON",
                Toast.LENGTH_SHORT
            ).show()

            Intent.ACTION_HEADSET_PLUG -> {
                val state = intent.getIntExtra("state", -1)
                Toast.makeText(
                    context,
                    if (state == 1) "Headset plugged in" else "Headset unplugged",
                    Toast.LENGTH_SHORT
                ).show()
            }

            Intent.ACTION_BOOT_COMPLETED -> Log.d("Receiver", "Device rebooted!")
            else -> Log.d("Receiver", "Broadcast received: " + action)
        }
    }
}