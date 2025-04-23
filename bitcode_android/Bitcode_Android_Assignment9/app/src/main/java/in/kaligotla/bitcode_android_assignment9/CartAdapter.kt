package `in`.kaligotla.bitcode_android_assignment9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.bitcode_android_assignment9.databinding.ItemCartBinding

class CartAdapter(
    private val onIncrement: (Product) -> Unit,
    private val onDecrement: (Product) -> Unit,
    private val onRemove: (Product) -> Unit
) : ListAdapter<CartItem, CartAdapter.CartViewHolder>(DiffCallback()) {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItem) {
            binding.textViewCartTitle.text = item.product.title
            binding.textViewCartPrice.text = "₹${item.product.price}"
            binding.textViewQuantity.text = item.quantity.toString()
            binding.textViewItemTotal.text = "₹${item.product.price * item.quantity}"

            binding.buttonPlus.setOnClickListener { onIncrement(item.product) }
            binding.buttonMinus.setOnClickListener { onDecrement(item.product) }
            binding.buttonRemove.setOnClickListener { onRemove(item.product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }
}