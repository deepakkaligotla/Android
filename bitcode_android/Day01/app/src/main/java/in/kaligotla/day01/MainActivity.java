package in.kaligotla.day01;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        TextView textView = new TextView(this);
        textView.setText("Welcome To Android At Bitcode!");
        textView.setPadding(5,5,5,5);
        textView.setTextSize(30.0F);
        textView.setLayoutParams(layoutParamsForViews);
        container.addView(textView);

        EditText editText = new EditText(this);
        editText.setLayoutParams(layoutParamsForViews);
        editText.setHint("Enter your name");
        container.addView(editText);

        Button btnSubmit = new Button(this);
        btnSubmit.setLayoutParams(layoutParamsForViews);
        btnSubmit.setText("Submit");
        container.addView(btnSubmit);

        setContentView(container);
    }
}