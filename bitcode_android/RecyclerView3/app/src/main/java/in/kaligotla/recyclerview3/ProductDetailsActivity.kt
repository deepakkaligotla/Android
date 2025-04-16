package `in`.kaligotla.recyclerview3

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import `in`.kaligotla.recyclerview3.databinding.ActivityProductDetailsBinding

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var productDetailsBinding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productDetailsBinding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(productDetailsBinding.root)
        bindData()
    }

    private fun bindData() {
        intent.extras?.let { productDetailsBinding.productImage.setImageResource(R.drawable.ic_launcher_background) }
        productDetailsBinding.productTitle.text = intent.extras?.getString("product_title")
        productDetailsBinding.productPrice.text = intent.extras?.getDouble("product_price").toString()
    }
}