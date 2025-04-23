package `in`.kaligotla.bitcode_android_assignment8

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.bitcode_android_assignment8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var products: ArrayList<Product>
    private var cartItemCount = 0
    private var cartBadge: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        initializeProductsData()
        activityMainBinding.productsRecyclerView.adapter = ProductAdapter(products) { product ->
            CartManager.addToCart(product)
            cartItemCount = CartManager.size()
            invalidateOptionsMenu()
            updateCartBadge()
        }
        activityMainBinding.productsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        cartItemCount = CartManager.size()
        invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val cartItem = menu.findItem(R.id.menu_cart)
        val actionView = cartItem.actionView
        cartBadge = actionView?.findViewById(R.id.cart_badge)
        val cartIcon = actionView?.findViewById<ImageView>(R.id.cart_icon)
        updateCartBadge()
        cartIcon?.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }
        return true
    }

    fun updateCartBadge() {
        cartBadge?.apply {
            text = cartItemCount.toString()
            visibility = if (cartItemCount > 0) View.VISIBLE else View.GONE
        }
    }

    fun initializeProductsData() {
        products = arrayListOf<Product>(
            Product(
                1,
                "Samosa",
                "Crispy deep-fried snack filled with spicy potatoes and peas.",
                15.00,
                "https://c.ndtvimg.com/2023-03/0m65kep_samosa_625x300_10_March_23.jpg",
                4.3f,
                32
            ),
            Product(
                2,
                "Poha",
                "Light and fluffy flattened rice cooked with onions, turmeric, and mustard seeds.",
                25.00,
                "https://www.mrishtanna.com/wp-content/uploads/2018/04/poha-indian-breakfast-recipe.jpg",
                4.1f,
                39
            ),
            Product(
                3,
                "Vada Pav",
                "Mumbai's iconic street food – spicy potato fritter in a bun.",
                20.00,
                "https://ministryofcurry.com/wp-content/uploads/2024/06/vada-pav-3.jpg",
                3.5f,
                54
            ),
            Product(
                4,
                "Sabudana Khichdi",
                "Tapioca pearls stir-fried with peanuts and spices – a fasting favorite.",
                25.00,
                "https://www.sharmispassions.com/wp-content/uploads/2022/02/sabudana-khichdi4.jpg",
                3.0f,
                62
            ),
            Product(
                5,
                "Upma",
                "Savory semolina dish cooked with vegetables and tempered spices.",
                25.00,
                "https://rakskitchen.net/wp-content/uploads/2013/02/upma-recipe-feat.jpg",
                3.8f,
                40
            ),
            Product(
                6,
                "Idli",
                "Soft, fluffy steamed rice cakes – a healthy South Indian breakfast.",
                30.00,
                "https://rakskitchen.net/wp-content/uploads/2010/07/idli-recipe.jpg",
                4.4f,
                51
            ),
            Product(
                7,
                "Dosa",
                "Crispy crepe made from fermented rice and lentil batter.",
                30.00,
                "https://rakskitchen.net/wp-content/uploads/2012/09/7982154820_48cefa8f32_o.jpg",
                4.0f,
                66
            ),
            Product(
                8,
                "Paratha",
                "Pan-fried Indian flatbread stuffed with veggies or spices.",
                50.00,
                "https://rakskitchen.net/wp-content/uploads/2012/02/6914919403_ed7d8ab396_z.jpg",
                4.6f,
                72
            ),
            Product(
                9,
                "Veg Thali",
                "A wholesome Indian vegetarian meal with rice, curries, and dessert.",
                90.00,
                "https://rakskitchen.net/wp-content/uploads/2014/12/dum-aloo-veg-pulao.jpg",
                3.9f,
                108
            ),
            Product(
                10,
                "Non Veg Thali",
                "A hearty Indian meal with meat curries, rice, breads, and sides.",
                120.00,
                "https://media-cdn.tripadvisor.com/media/photo-m/1280/15/fe/3b/a8/non-veg-thali.jpg",
                3.6f,
                132
            )
        )
    }
}