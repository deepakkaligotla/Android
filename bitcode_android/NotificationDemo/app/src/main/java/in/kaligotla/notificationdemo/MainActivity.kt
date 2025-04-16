package `in`.kaligotla.notificationdemo

import android.Manifest
import android.app.ActionBar
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BigPictureStyle
import androidx.core.app.NotificationCompat.InboxStyle
import androidx.core.app.NotificationCompat.PRIORITY_HIGH
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.PendingIntentCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import `in`.kaligotla.notificationdemo.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var notificationManager: NotificationManagerCompat
    private val notificationPermission: String = Manifest.permission.POST_NOTIFICATIONS
    private val CHANNEL_ID = "BitCode Notification Channel"
    private val SIMPLE_NOTIFICATION_ID = 1
    private val BIG_PICTURE_NOTIFICATION_ID = 2
    private val INBOX_NOTIFICATION_ID = 3
    private val MESSAGING_NOTIFICATION_ID = 4

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        notificationManager = NotificationManagerCompat.from(this)
        createNotificationChannel()
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {}

        if (ContextCompat.checkSelfPermission(
                this,
                notificationPermission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(notificationPermission)
        }

        activityMainBinding.btnSimpleNotification.setOnClickListener {
            simpleNotification()
        }


        activityMainBinding.btnBigPictureNotification.setOnClickListener {
            bigPictureNotification()
        }

        activityMainBinding.btnInboxNotification.setOnClickListener {
            inboxStyleNotification()
        }

        activityMainBinding.btnActionTextNotification.setOnClickListener {
            actionTextStyleNotification()
        }

        activityMainBinding.btnSnackbarNotification.setOnClickListener {
            snackBarNotification()
        }
    }

    private fun createNotificationChannel() {
        val notificationChannel = NotificationChannelCompat.Builder(
            CHANNEL_ID,
            NotificationManagerCompat.IMPORTANCE_MAX
        )
        notificationChannel.setName("Bitcode Channel")
        notificationChannel.setDescription("Bitcode notification for Android")
        notificationManager.createNotificationChannel(notificationChannel.build())
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun simpleNotification() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Simple Notification")
            .setContentText("Android Batch timings")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setLights(Color.RED, 20, 10)
            .setVisibility(NotificationCompat.VISIBILITY_SECRET)
            .setVibrate(longArrayOf(0, 500, 250, 500))
            .setContentIntent(
            PendingIntentCompat.getActivity(
                this,
                1,
                Intent(this, DetailsActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_ONE_SHOT,
                true
            )
        )
        notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification.build())
    }

    @RequiresApi(Build.VERSION_CODES.S)
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun bigPictureNotification() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.bitcode)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setLights(Color.RED, 20, 10)
            .setVisibility(NotificationCompat.VISIBILITY_SECRET)
            .setVibrate(longArrayOf(0, 500, 250, 500))
            .setStyle(
                BigPictureStyle()
                    .setBigContentTitle("Big Picture Title")
                    .setSummaryText("Big Picture Summary Text")
                    .setContentDescription("Big Picture Description")
                    .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.bitcode))
                    .bigLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.bitcode))
            )
            .setContentIntent(
                PendingIntentCompat.getActivity(
                    this,
                    1,
                    Intent(this, DetailsActivity::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_ONE_SHOT,
                    true
                )
            )
        notificationManager.notify(BIG_PICTURE_NOTIFICATION_ID, notification.build())
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun inboxStyleNotification() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.bitcode)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setLights(Color.RED, 20, 10)
            .setVisibility(NotificationCompat.VISIBILITY_SECRET)
            .setVibrate(longArrayOf(0, 500, 250, 500))
            .setStyle(
                InboxStyle()
                    .setBigContentTitle("Inbox Style Title")
                    .setSummaryText("Inbox Style Text")
                    .addLine("New Line 1")
                    .addLine("New Line 2")
                    .addLine("New Line 3")
                    .addLine("New Line 4")
                    .addLine("New Line 5")
                    .addLine("New Line 6")
                    .addLine("New Line 7")
                    .addLine("New Line 8") //Only 7 lines
            )
            .setContentIntent(
                PendingIntentCompat.getActivity(
                    this,
                    1,
                    Intent(this, DetailsActivity::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_ONE_SHOT,
                    true
                )
            )
        notificationManager.notify(INBOX_NOTIFICATION_ID, notification.build())
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun actionTextStyleNotification(){
        var notificationCompat = NotificationCompat.Builder(this, CHANNEL_ID)
        notificationCompat.setSmallIcon(R.drawable.bitcode)
        var intent = Intent(this,DetailsActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this,
            1,
            intent,
            PendingIntent.FLAG_MUTABLE
        )
        val actionTextStyle1 = NotificationCompat.Action(
            R.drawable.bitcode,
            "Android Placements April '25",
            pendingIntent
        )
        val actionTextStyle2= NotificationCompat.Action(
            R.drawable.bitcode,
            "Android Placements April '25",
            pendingIntent
        )
        notificationCompat.setContentTitle("Action Text")
        notificationCompat.setContentText("List of Placed Students - Jay Rathod, Lokesh Kapse, Rohit")
        notificationCompat.setPriority(PRIORITY_HIGH)
        notificationCompat.setLights(Color.YELLOW,20,20)
        notificationCompat.setVibrate(LongArray(30) { i -> (i + 10).toLong() })
        notificationCompat.setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
        var bitmapImage = BitmapFactory.decodeResource(resources,R.drawable.bitcode)
        notificationCompat.setLargeIcon(bitmapImage)
        notificationCompat.addAction(actionTextStyle1)
        notificationCompat.addAction(actionTextStyle2)
        notificationManager.notify(MESSAGING_NOTIFICATION_ID, notificationCompat.build())
    }

    private fun snackBarNotification(){
        var textView : TextView = TextView(this)
        var layoutParams = ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = layoutParams
        textView.setTextColor(Color.BLACK)
        activityMainBinding.main.addView(textView)
        textView.text = "Android Feb '25"
        var snackBar = Snackbar.make(this,
            textView,
            "Snack Bar",
            Snackbar.LENGTH_LONG)
        snackBar.setBackgroundTint(Color.GREEN)
        snackBar.setAction("Click Me") {
            Toast.makeText(this, "Snackbar Closed", Toast.LENGTH_SHORT).show()
        }
        snackBar.show()
    }
}