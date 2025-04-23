package `in`.kaligotla.bitcode_android_assignment8

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.bitcode_android_assignment8.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var activityCartBinding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCartBinding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(activityCartBinding.root)
        supportActionBar?.hide()
        activityCartBinding.recyclerViewCart.adapter = CartAdapter(CartManager.getMyCartItems())
        activityCartBinding.recyclerViewCart.layoutManager = LinearLayoutManager(this)
        activityCartBinding.textViewTotal.text = "Total: ₹${CartManager.getTotalPrice()}"
        activityCartBinding.btnCheckout.setOnClickListener {
            Toast.makeText(this, "Checkout not implemented!", Toast.LENGTH_SHORT).show()
        }
    }
}