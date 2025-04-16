package `in`.kaligotla.recyclerview3

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.recyclerview3.databinding.ProductViewHolderBinding
import `in`.kaligotla.recyclerview3.databinding.UserViewHolderBinding


class ProductsAdapter(private val users: ArrayList<User>,
                      private val products: ArrayList<Product>) : RecyclerView.Adapter<ProductsAdapter.UsersViewHolder>() {

    companion object {
        private const val TYPE_USER = 0
        private const val TYPE_PRODUCT = 1
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userVHBinding: UserViewHolderBinding = UserViewHolderBinding.bind(itemView)

        init {
            userVHBinding.imgViewU.setOnClickListener{
                var intent = Intent(itemView.context, UserDetailsActivity::class.java)
                var user = users[adapterPosition/3]
                intent.putExtra("user", user)
                itemView.context.startActivity(intent)
            }
        }
    }

    inner class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productVHBinding: ProductViewHolderBinding = ProductViewHolderBinding.bind(itemView)
        init {
            productVHBinding.imgViewP.setOnClickListener {
                var intent = Intent(itemView.context, ProductDetailsActivity::class.java)
                intent.putExtra("product_title", productVHBinding.textViewTitle.text)
                intent.putExtra("product_price", productVHBinding.textViewPrice.text.toString().replace("₹", "").trim().toDouble())
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % 3 == 0) TYPE_USER else TYPE_PRODUCT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_USER) {
            UsersViewHolder(UserViewHolderBinding.inflate(LayoutInflater.from(parent.context)).root)
        } else {
            ProductsViewHolder(ProductViewHolderBinding.inflate(LayoutInflater.from(parent.context)).root)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_USER) {
            val userHolder = holder as UsersViewHolder
            val userIndex = position / 3
            if (userIndex < users.size) {
                val user = users[userIndex]
                userHolder.userVHBinding.imgViewU.setImageResource(R.drawable.ic_launcher_foreground)
                userHolder.userVHBinding.textViewName.text = user.name
                userHolder.userVHBinding.textViewDob.text = user.dob.toString()
            }
        } else {
            val productHolder = holder as ProductsViewHolder
            val productIndex = position - (position / 3)
            if (productIndex < products.size) {
                val product = products[productIndex]
                productHolder.productVHBinding.imgViewP.setImageResource(R.drawable.ic_launcher_background)
                productHolder.productVHBinding.imgViewP.clipToOutline = true
                productHolder.productVHBinding.textViewTitle.text = product.title
                productHolder.productVHBinding.textViewPrice.text = "₹${product.price}"
            }
        }
    }

    override fun getItemCount(): Int {
        return users.size + products.size
    }
}