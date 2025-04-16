package `in`.kaligotla.retrofitdemo2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import `in`.kaligotla.retrofitdemo2.databinding.ProductViewHolderBinding

class ProductsAdapter(private var products: ArrayList<APIResponse>): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    inner class ProductsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var productsViewHolderBinding = ProductViewHolderBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(ProductViewHolderBinding.inflate(LayoutInflater.from(parent.context)).root)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(products[position].image).into(holder.productsViewHolderBinding.productImageView)
        holder.productsViewHolderBinding.productTitle.text = products[position].title
        holder.productsViewHolderBinding.productDescription.text = products[position].description
        holder.productsViewHolderBinding.productPrice.text = products[position].price.toString()
        holder.productsViewHolderBinding.productCategory.text = products[position].category
        holder.productsViewHolderBinding.productRating.text = products[position].rating.rate.toString()
        holder.productsViewHolderBinding.productCount.text = products[position].rating.count.toString()
    }

    override fun getItemCount(): Int {
        return products.size
    }
}