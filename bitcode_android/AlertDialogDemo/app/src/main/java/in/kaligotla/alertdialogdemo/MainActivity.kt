package `in`.kaligotla.alertdialogdemo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import `in`.kaligotla.alertdialogdemo.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.alertDialog.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
            builder.setIcon(R.drawable.ic_launcher_background)
            builder.setTitle("Title")
            builder.setMessage("Message")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this@MainActivity,"$dialog -- $which", Toast.LENGTH_SHORT).show() //which -- 1 -- Positive
            })
            builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this@MainActivity,"$dialog -- $which", Toast.LENGTH_SHORT).show() //which -- 2 -- Negative
            })
            builder.setNeutralButton("Maybe", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this@MainActivity,"$dialog -- $which", Toast.LENGTH_SHORT).show() //which -- 3 -- Neutral
            })
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }

        activityMainBinding.date.setOnClickListener {
            val datePickerDialog: DatePickerDialog = DatePickerDialog(
                this@MainActivity,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    Toast.makeText(this@MainActivity,"mm/dd/yyyy - $dayOfMonth/${month+1}/$year", Toast.LENGTH_SHORT).show()
                },
                LocalDate.now().year,
                LocalDate.now().monthValue-1,
                LocalDate.now().dayOfMonth
            )
            datePickerDialog.show()
        }

        activityMainBinding.time.setOnClickListener {
            val timePickerDialog: TimePickerDialog = TimePickerDialog(
                this@MainActivity,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    Toast.makeText(this@MainActivity, "hh:mm - $hourOfDay:$minute", Toast.LENGTH_SHORT).show()
                },
                LocalTime.now().hour,
                LocalTime.now().minute,
                false
            )
            timePickerDialog.show()
        }
    }
}