package `in`.kaligotla.bitcode_android_assignment8

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import `in`.kaligotla.bitcode_android_assignment8.databinding.ProductViewHolderBinding

class ProductAdapter(private var products: ArrayList<Product>,
                     private val onAddToCart: (Product) -> Unit): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var productViewHolderBinding = ProductViewHolderBinding.bind(itemView)
    }
    override fun getItemCount(): Int {
        return products.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductViewHolderBinding.inflate(LayoutInflater.from(parent.context)).root)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productViewHolderBinding.productTitle.text = products[position].title
        holder.productViewHolderBinding.productPrice.text = "₹ ${products[position].price}"
        holder.productViewHolderBinding.productRatingValue.text = products[position].rating.toString()
        holder.productViewHolderBinding.productStarRating.rating = products[position].rating
        holder.productViewHolderBinding.productRatingCount.text = "(${products[position].ratingCount})"
        Glide.with(holder.itemView.context).load(products[position].image).into(holder.productViewHolderBinding.productImage)
        holder.productViewHolderBinding.btnAddToCart.setOnClickListener {
            onAddToCart(products[position])
        }
        holder.productViewHolderBinding.productCard.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProductDetailsActivity::class.java)
            intent.putExtra("product", products[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}