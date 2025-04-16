package in.kaligotla.recyclerview_1;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.divider.MaterialDivider;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewAdapterViewHolder> {
    private ArrayList<Product> data;
    public RecyclerViewAdapter(ArrayList<Product> getData) {
        this.data = getData;
    }
    class RecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder {
        LinearLayout viewHolderContainer;
        public RecyclerViewAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.e("Recycler View","RecyclerViewAdapterViewHolder Constructor");
            viewHolderContainer = (LinearLayout) itemView;
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("Recycler View","onCreateViewHolder");
        LinearLayout container = new LinearLayout(parent.getContext());
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(parent.getLayoutParams());
        container.setPadding(20, 60, 40, 20);
        container.setBackgroundColor(Color.GRAY);

        TextView prId = new TextView(parent.getContext());
        prId.setId(View.generateViewId());
        prId.setText("Product ID");

        TextView prName = new TextView(parent.getContext());
        prName.setId(View.generateViewId());
        prName.setText("Product Name");

        TextView prPrice = new TextView(parent.getContext());
        prPrice.setId(View.generateViewId());
        prPrice.setText("Product Price");

        MaterialDivider divider = new MaterialDivider(parent.getContext());
        divider.setPadding(20, 20, 20, 20);

        container.addView(prId);
        container.addView(prName);
        container.addView(prPrice);
        container.addView(divider);

        return new RecyclerViewAdapterViewHolder(container);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterViewHolder holder, int position) {
        Log.e("Recycler View","onBindViewHolder");
        ((TextView) holder.viewHolderContainer.findViewById(holder.viewHolderContainer.getChildAt(0).getId()))
                .setText("Id: "+data.get(position).getProductId());
        ((TextView) holder.viewHolderContainer.findViewById(holder.viewHolderContainer.getChildAt(1).getId()))
                .setText("Name: " + data.get(position).getProductName());
        ((TextView) holder.viewHolderContainer.findViewById(holder.viewHolderContainer.getChildAt(2).getId()))
                .setText("Price: $" + data.get(position).getProductPrice());
    }

    @Override
    public int getItemCount() {
        Log.e("Recycler View","getItemCount");
        return data.size();
    }
}
