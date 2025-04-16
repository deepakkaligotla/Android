package in.kaligotla.recyclerview_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
    ArrayList<Product> products;
    ImageView imageView;
    TextView txtViewProdName, txtViewProdPrice;

    public RVAdapter(ArrayList<Product> productArrayList) {
        this.products = productArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
            txtViewProdName = itemView.findViewById(R.id.txtViewProdName);
            txtViewProdPrice = itemView.findViewById(R.id.txtViewProdPrice);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder, null);

        imageView = view.findViewById(R.id.imgView);
        txtViewProdName = view.findViewById(R.id.txtViewProdName);
        txtViewProdPrice = view.findViewById(R.id.txtViewProdPrice);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = products.get(position);

        ImageView imageView1 = holder.itemView.findViewById(R.id.imgView);
        TextView prName = holder.itemView.findViewById(R.id.txtViewProdName);
        TextView prPrice = holder.itemView.findViewById(R.id.txtViewProdPrice);

        imageView1.setImageResource(R.drawable.ic_launcher_background);
        prName.setText(product.getProdName());
        prPrice.setText("₹"+String.format("%.2f", product.getProdPrice()));

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Position -- "+position, Toast.LENGTH_SHORT).show();
            }
        });

        prName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), product.getProdName()+"clicked", Toast.LENGTH_SHORT).show();
            }
        });

        prPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), String.format("%.2f", product.getProdPrice())+"  clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
