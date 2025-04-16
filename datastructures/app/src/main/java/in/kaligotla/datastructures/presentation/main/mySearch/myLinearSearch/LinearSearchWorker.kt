package `in`.kaligotla.datastructures.presentation.main.mySearch.myLinearSearch

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import `in`.kaligotla.datastructures.R
import `in`.kaligotla.datastructures.data.domain.model.entities.Location

@HiltWorker
class LinearSearchWorker @AssistedInject constructor(
    @Assisted context: Context, @Assisted params: WorkerParameters
) : CoroutineWorker(context, params) {
    val key = inputData.getInt("KEY_PINCODE", 0)
    private val index = inputData.getInt("INDEX", 0)
    var locationsList by mutableStateOf(emptyList<Location>())
    var items by mutableStateOf(emptyList<Location>().toMutableList())
    private val progress = "Starting Search"

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        setForeground(createForegroundInfo(progress))
        return linearSearchRec(key, index)
    }

    private fun linearSearchRec(key: Int, index: Int): Result {
        if (index == locationsList.size) {
            items.clear()
            return Result.success(Data.Builder().putString("items", "List is empty").build())
        }
        if (key == locationsList[index].pincode) {
            items.clear()
            items.add(locationsList[index])
            return Result.success(Data.Builder().putString("items", "Key Found").build())
        } else {
            linearSearchRec(key, index + 1)
            return Result.success(Data.Builder().putString("items", "Finding Key").build())
        }
        return Result.failure(Data.Builder().putString("items", "Finding Key").build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createForegroundInfo(progress: String): ForegroundInfo {
        val id = applicationContext.getString(R.string.notification_channel_id)
        val title = applicationContext.getString(R.string.notification_title)
        val cancel = applicationContext.getString(R.string.cancel_download)
        val intent = WorkManager.getInstance(applicationContext)
            .createCancelPendingIntent(getId())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }

        val notification = NotificationCompat.Builder(applicationContext, id)
            .setContentTitle(title)
            .setTicker(title)
            .setContentText(progress)
            .setSmallIcon(R.drawable.ic_work_notification)
            .setOngoing(true)
            .addAction(android.R.drawable.ic_delete, cancel, intent)
            .build()
        return ForegroundInfo(notification.channelId.toInt(), notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = applicationContext.getString(R.string.channel_name)
            val descriptionText = applicationContext.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(
                applicationContext.getString(R.string.notification_channel_id),
                name,
                importance
            )
            mChannel.description = descriptionText
            val notificationManager =
                applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}