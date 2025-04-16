package in.kaligotla.assignment01;

import android.graphics.Color;
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

public class MainActivity extends AppCompatActivity {
    TextView textView1, textView2, textView3;
    EditText editText;
    Button btn;

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

        textView1 = new TextView(this);
        textView1.setLayoutParams(layoutParamsForViews);
        textView1.setPadding(5,5,5,5);
        textView1.setTextSize(30.0F);
        textView1.setBackgroundColor(Color.BLUE);
        container.addView(textView1);

        container.addView(new TextView(this));

        textView2 = new TextView(this);
        textView2.setLayoutParams(layoutParamsForViews);
        textView2.setPadding(5,5,5,5);
        textView2.setTextSize(30.0F);
        textView2.setBackgroundColor(Color.BLUE);
        container.addView(textView2);

        container.addView(new TextView(this));

        textView3 = new TextView(this);
        textView3.setLayoutParams(layoutParamsForViews);
        textView3.setPadding(5,5,5,5);
        textView3.setTextSize(30.0F);
        textView3.setBackgroundColor(Color.BLUE);
        container.addView(textView3);

        container.addView(new TextView(this));

        editText = new EditText(this);
        editText.setLayoutParams(layoutParamsForViews);
        editText.setHint("Enter number");
        container.addView(editText);

        btn = new Button(this);
        btn.setText("Submit");
        container.addView(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("1")) {
                    textView1.setBackgroundColor(Color.RED);
                    textView2.setBackgroundColor(Color.BLUE);
                    textView3.setBackgroundColor(Color.BLUE);
                }
                else if(editText.getText().toString().equals("2")) {
                    textView2.setBackgroundColor(Color.RED);
                    textView1.setBackgroundColor(Color.BLUE);
                    textView3.setBackgroundColor(Color.BLUE);
                }
                else if(editText.getText().toString().equals("3")) {
                    textView3.setBackgroundColor(Color.RED);
                    textView1.setBackgroundColor(Color.BLUE);
                    textView2.setBackgroundColor(Color.BLUE);
                } else {
                    textView1.setBackgroundColor(Color.GREEN);
                    textView2.setBackgroundColor(Color.GREEN);
                    textView3.setBackgroundColor(Color.GREEN);
                }
            }
        });

        setContentView(container);
    }
}