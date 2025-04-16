package `in`.kaligotla.recyclerview3

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.recyclerview3.databinding.ActivityMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var users: ArrayList<User>
    private lateinit var products: ArrayList<Product>
    private lateinit var productsAdapter: ProductsAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(activityMainBinding.root)
        initArray()
        initViews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initArray() {
        products = ArrayList<Product>()
        for(i in 1..10) {
            products.add(Product(
                i,
                "Product $i",
                i * 100 + 18.99
            ))
        }
        users = ArrayList<User>()
        for(i in 1..5) {
            users.add(User(
                i,
                "Username $i",
                LocalDate.of(2007, 3, 25).plusDays(i.toLong())
            ))
        }
    }

    private fun initViews() {
        productsAdapter = ProductsAdapter(users, products)
        activityMainBinding.recyclerView.adapter = productsAdapter
        activityMainBinding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}