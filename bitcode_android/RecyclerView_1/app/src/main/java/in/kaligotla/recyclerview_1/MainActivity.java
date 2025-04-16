package in.kaligotla.recyclerview_1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Product> arrayList;
    RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViews();
    }

    private void initData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            arrayList.add(new Product("Product Name "+ i, 100 + i, i * 500 + 18.5));
        }
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        ));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}