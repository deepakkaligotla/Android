package `in`.kaligotla.intentfilterdemo2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.view.SubMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.SubMenuBuilder
import `in`.kaligotla.intentfilterdemo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .build()
        )

        activityMainBinding.btnCall.setOnClickListener {
            var intent = Intent(Intent.ACTION_CALL)
            intent.setData(
                Uri.parse(
                    activityMainBinding.edtUri.text.toString()
                )
            )
            startActivity(intent)
        }

        activityMainBinding.btnPlayAudio.setOnClickListener {
            var intent = Intent(Intent.ACTION_SEND)
            intent.action = "android.intent.action.VIEW"
            intent.setDataAndType(
                Uri.parse(
                    activityMainBinding.edtUri.text.toString()
                ),
                "audio/mp3"
            )
            startActivity(intent)
        }


        activityMainBinding.btnViewImage.setOnClickListener {
            var intent = Intent("android.intent.action.VIEW")

            intent.setDataAndType(
                Uri.parse(activityMainBinding.edtUri.text.toString()),
                "image/jpeg"
            )
            startActivity(intent)
        }

        activityMainBinding.btnWeb.setOnClickListener {
            var intent = Intent("android.intent.action.VIEW")
            intent.action = Intent.ACTION_VIEW
            intent.setData(
                Uri.parse(
                    activityMainBinding.edtUri.text.toString()
                )
            )
            startActivity(intent)
        }
        
        //file:///storage/emulated/0/Pictures/image1.png
        //file:///storage/emulated/0/Music/music.mp3
    }
}