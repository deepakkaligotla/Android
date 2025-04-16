package in.kaligotla.day02;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    EditText editText;
    Button btnSubmit1, btnSubmit2, btnSubmit3, btnSubmit4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(20,20,20,20);
        container.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams layoutParamsForContainer = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        container.setLayoutParams(layoutParamsForContainer);

        LinearLayout.LayoutParams layoutParamsForViews = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        textView = new TextView(this);
        textView.setText("Welcome To Android At Bitcode!");
        textView.setPadding(5,5,5,5);
        textView.setTextSize(30.0F);
        textView.setLayoutParams(layoutParamsForViews);
        container.addView(textView);

        editText = new EditText(this);
        editText.setLayoutParams(layoutParamsForViews);
        editText.setHint("Enter your name");
        container.addView(editText);

        //1st way - implements
        btnSubmit1 = new Button(this);
        btnSubmit1.setLayoutParams(layoutParamsForViews);
        btnSubmit1.setOnClickListener(this);
        btnSubmit1.setText("Submit 1");
        container.addView(btnSubmit1);

        //2nd way - Object of inner class
        btnSubmit2 = new Button(this);
        btnSubmit2.setLayoutParams(layoutParamsForViews);
        btnSubmit2.setOnClickListener(new MyBtnSubmitClickListner());
        btnSubmit2.setText("Submit 2");
        container.addView(btnSubmit2);

        //3rd Way - Object of anonymous class
        btnSubmit3 = new Button(this);
        btnSubmit3.setLayoutParams(layoutParamsForViews);
        btnSubmit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Welcome " + editText.getText().toString() + "\nAnonymous class");
            }
        });
        btnSubmit3.setText("Submit 3");
        container.addView(btnSubmit3);

        //4th Way - reference of interface to object of class
        View.OnClickListener listner = new MyBtnSubmitClickListner();
        btnSubmit4 = new Button(this);
        btnSubmit4.setLayoutParams(layoutParamsForViews);
        btnSubmit4.setOnClickListener(listner);
        btnSubmit4.setText("Submit 4");
        container.addView(btnSubmit4);

        setContentView(container);
    }

    /* 4 WAYS to implement onClickListner */
    //Interface methods should be implemented mandate
    @Override
    public void onClick(View v) {
        if(v == btnSubmit1) {
            textView.setText("Welcome " + editText.getText().toString() + "\nimplements");
        }
    }

    //2nd way
    //inner classes in java - 4 ways
    //anonymous, static,
    class MyBtnSubmitClickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(v == btnSubmit2) {
                textView.setText("Welcome " + editText.getText().toString() + "\ninner class");
            }
            if(v == btnSubmit4) {
                textView.setText("Welcome " + editText.getText().toString() + "\nreference of interface to object of class");
            }
        }
    }
}