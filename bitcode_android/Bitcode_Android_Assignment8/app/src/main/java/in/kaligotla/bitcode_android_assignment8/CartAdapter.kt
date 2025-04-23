package `in`.kaligotla.bitcode_android_assignment8

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import `in`.kaligotla.bitcode_android_assignment8.databinding.CartItemBinding

class CartAdapter(private val cartItems: List<Product>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartItems[position]
        holder.binding.cartProductTitle.text = product.title
        holder.binding.cartProductPrice.text = "₹ ${product.price}"
        Glide.with(holder.itemView.context).load(product.image).into(holder.binding.cartProductImage)
    }
}