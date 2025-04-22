package `in`.kaligotla.servicesdemo1

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.util.Log
import androidx.core.net.toUri

class MediaPlayerService: Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var uri: Uri? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val fileUriString = intent?.getStringExtra("file_uri")
        if (fileUriString == null) {
            stopSelf()
            return START_NOT_STICKY
        }
        Log.e("Tag", fileUriString)
        uri = fileUriString.toUri()
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(this, uri)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        } catch (e: Exception) {
            Log.e("MediaPlayerService", "Error playing media: ${e.message}")
            stopSelf()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}