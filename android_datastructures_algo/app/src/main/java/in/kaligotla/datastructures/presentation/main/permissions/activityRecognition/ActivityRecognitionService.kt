package `in`.kaligotla.datastructures.presentation.main.permissions.activityRecognition

import android.app.IntentService
import android.content.Intent
import com.google.android.gms.location.ActivityRecognitionResult

class ActivityRecognitionService : IntentService("ActivityRecognitionService") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null && ActivityRecognitionResult.hasResult(intent)) {
            val result = ActivityRecognitionResult.extractResult(intent)
            val detectedActivity = result?.mostProbableActivity

            // Broadcast the detected activity to the receiver
            val broadcastIntent = Intent(ACTION_ACTIVITY_RECOGNITION)
            broadcastIntent.putExtra(EXTRA_DETECTED_ACTIVITY, detectedActivity)
            sendBroadcast(broadcastIntent)
        }
    }

    companion object {
        const val ACTION_ACTIVITY_RECOGNITION =
            "com.example.activityrecognition.ACTION_ACTIVITY_RECOGNITION"
        const val EXTRA_DETECTED_ACTIVITY = "detected_activity"
    }
}