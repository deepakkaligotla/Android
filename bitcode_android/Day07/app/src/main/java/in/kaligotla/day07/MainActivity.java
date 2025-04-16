package in.kaligotla.day07;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button alertDialog, datePickerDialog, timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setButtonListeners();
    }

    private void initViews() {
        alertDialog = findViewById(R.id.alertDialog);
        datePickerDialog = findViewById(R.id.datePickerDialog);
        timePickerDialog = findViewById(R.id.timePickerDialog);
    }

    private void setButtonListeners() {
        View.OnClickListener obj = new BtnOnClickListener();
        alertDialog.setOnClickListener(obj);
        datePickerDialog.setOnClickListener(obj);
        timePickerDialog.setOnClickListener(obj);
    }

    class BtnOnClickListener implements View.OnClickListener {
        DialogInterface.OnClickListener obj = new DialogButtonOnClickListner();
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.alertDialog) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Submit Exam");
                builder.setMessage("Are you sure");
                builder.setIcon(R.drawable.ic_launcher_background);
                builder.setPositiveButton("Yes", obj);
                builder.setNegativeButton("No", obj);
                builder.setNeutralButton("Neutral", obj);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if(v.getId() == R.id.datePickerDialog) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerOnClickListener(),
                        2025, 2, 6
                );
                datePickerDialog.show();
            } else if(v.getId() == R.id.timePickerDialog) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerOnClickListener(),
                        4, 55, true
                );
                timePickerDialog.show();
            }
        }
    }

    class TimePickerOnClickListener implements  TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Toast.makeText(MainActivity.this, hourOfDay+":"+minute, Toast.LENGTH_LONG).show();
        }
    }

    class DatePickerOnClickListener implements  DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Toast.makeText(MainActivity.this, dayOfMonth+"/"+(month+1)+"/"+year, Toast.LENGTH_LONG).show();
        }
    }

    class DialogButtonOnClickListner implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case -1:
                    Toast.makeText(MainActivity.this, "Positive Clicked", Toast.LENGTH_LONG).show();
                    break;
                case -2:
                    Toast.makeText(MainActivity.this, "Negative Clicked", Toast.LENGTH_LONG).show();
                    break;
                case -3:
                    Toast.makeText(MainActivity.this, "Neutral Clicked", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    /*
    Dialog: Is the alert to the user,
        * Alert - 3 buttons (Positive -1, Negative -2, Neutral -3)
        * DatePicker
        * TimePicker
        * Progress
        * Selected / Custom
    */
}