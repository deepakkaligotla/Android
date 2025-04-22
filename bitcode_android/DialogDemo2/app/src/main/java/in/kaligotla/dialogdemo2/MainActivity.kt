package `in`.kaligotla.dialogdemo2

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnCustomDialog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCustomDialog = findViewById(R.id.btnCustomDialog)
        btnCustomDialog.setOnClickListener {
            val customDialog = CustomDialog(this@MainActivity)
            customDialog.setTitle("Custom Dialog")
            customDialog.show()
        }
    }
}