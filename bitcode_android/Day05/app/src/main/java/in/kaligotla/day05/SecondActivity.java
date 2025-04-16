package in.kaligotla.day05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    String name, city;
    int rollNumber;

    Button btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        extractData();
        initListeners();
    }

    public void initViews() {
        textView = findViewById(R.id.txtView);
        btnPrevious = findViewById(R.id.btnPrevious);
    }

    public void extractData() {
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        name = bundle.getString("name");
        rollNumber = bundle.getInt("roll_number");
        city = bundle.getString("city");
        textView.setText(name+ ", "+ rollNumber + ", " + city);
    }

    public void initListeners() {
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("state", "Maharashtra");
                setResult(1,intent);
//                finish();
            }
        });
    }
}