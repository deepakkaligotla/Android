package in.kaligotla.recyclerview_2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Product> products;
    private RVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViews();
    }

    private void initData() {
        products = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            products.add(new Product(100+i, "Product "+i, i * 250.30 + 18));
        }
        rvAdapter = new RVAdapter(products);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        ));
    }
}