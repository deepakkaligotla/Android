package in.kaligotla.day05;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    EditText edtText;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initViews();
        setListeners();
    }

    public void initViews() {
        edtText = findViewById(R.id.edtText);
        btnNext = findViewById(R.id.btnNext);
    }

    //intent in android
    //it helps in communication between 2 activities, senderActivity to OS to receiverActivity
    //Intent have 2 parts
    //* Data: Data, Action, Category
    //* ExtraData: Real data, how much we want

    //Anonymous inner class which gets created, to get outer class context use OuterClassName.this
    public void setListeners() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("name", edtText.getText().toString());
                intent.putExtra("roll_number", 24);
                intent.putExtra("city", "Nagpur");
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle b = data.getExtras();
        String state = b.getString("state");
        Log.e("tag", state);
    }
}