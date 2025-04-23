package `in`.kaligotla.bitcode_android_assignment8

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import `in`.kaligotla.bitcode_android_assignment8.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var activityProductDetailsBinding: ActivityProductDetailsBinding
    private lateinit var selectedProduct: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityProductDetailsBinding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(activityProductDetailsBinding.root)
        getSelectedProduct()
        bindDataToViews()
    }

    fun getSelectedProduct() {
        val bundle = intent.extras
        selectedProduct = bundle?.getSerializable("product") as Product
    }

    fun bindDataToViews() {
        Glide.with(this).load(selectedProduct.image).into(activityProductDetailsBinding.productImage)
        activityProductDetailsBinding.productTitle.text = selectedProduct.title
        activityProductDetailsBinding.productPrice.text = "₹ ${selectedProduct.price}"
        activityProductDetailsBinding.productRatingValue.text = "${selectedProduct.rating}"
        activityProductDetailsBinding.productStarRating.rating = selectedProduct.rating
        activityProductDetailsBinding.productRatingCount.text = "(${selectedProduct.ratingCount})"
        activityProductDetailsBinding.productDescription.text = selectedProduct.description
        activityProductDetailsBinding.btnAddToCart.setOnClickListener {

        }
    }
}