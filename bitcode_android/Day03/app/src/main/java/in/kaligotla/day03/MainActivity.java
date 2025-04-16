package in.kaligotla.day03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView imgView;
    TextView welcomeTxtView;
    EditText edtUserName;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        bindData();
        setListeners();
    }

    private void initViews() {
        imgView = findViewById(R.id.imgView);
        welcomeTxtView = findViewById(R.id.welcomeTxtView);
        edtUserName = findViewById(R.id.edtUserName);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void bindData() {
        imgView.setImageResource(R.drawable.ic_launcher_background);
        welcomeTxtView.setText("Welcome to BitCode Technologies");
    }

    private void setListeners() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeTxtView.setText("Welcome "+edtUserName.getText());
            }
        });
    }

    /*
    Resources is a class in Android, cannot create object, use "getResources()".
    Types of resources -
        * Drawable & MipMap -> (images)
        * Layout -> (UI, xml files)
        * values -> (Colors, Strings, integers, array, typed-array, dimens.xml (dimensions), styles, booleans)
        * xml ->
    */
}