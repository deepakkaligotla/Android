package `in`.kaligotla.roomdbdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import java.util.LinkedList

class MainActivity : AppCompatActivity(){
    private var products = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var db = Room.databaseBuilder(this,
            ProductDatabase::class.java,
            "db_products")
            .allowMainThreadQueries()
            .build()

        var productDao = db.getProductDao()

        var product1 = Product(1001,"Laptop",54846)
//        productDao.insertProduct(product1)
//
//        for (i in 1..10){
//            productDao.insertProduct(Product(i + 100,
//                                             "Item $i",
//                                             i * 1000 + 500))
//        }


        //getAllProducts
//        var cursor = productDao.getAllProducts()
//        while (cursor.moveToNext()){
//            var prId = cursor.getInt(0)
//            var prTitle = cursor.getString(1)
//            var prPrice = cursor.getInt(2)
//
//            products.add(Product(prId,prTitle,prPrice))
//        }
//
//        for(eachProduct in products){
//            Log.e("tag", "${eachProduct.id} -- ${eachProduct.title} -- ${eachProduct.price}")
//        }

        var rowsAffected = productDao.delete(101)
        Log.e("tag", "$rowsAffected")


        var cursor1 = productDao.getAllProducts()
        while (cursor1.moveToNext()){
            var prId = cursor1.getInt(0)
            var prTitle = cursor1.getString(1)
            var prPrice = cursor1.getInt(2)

            products.add(Product(prId,prTitle,prPrice))
        }

        for(eachProduct in products){
            Log.e("tag", "${eachProduct.id} -- ${eachProduct.title} -- ${eachProduct.price}")
        }

        var rowsAffectedByUpdation = productDao.updateProduct(Product(102,"Mixer", 3422))
        Log.e("tag", "$rowsAffectedByUpdation")

        var cursor2 = productDao.getAllProducts()
        while (cursor2.moveToNext()){
            var prId = cursor2.getInt(0)
            var prTitle = cursor2.getString(1)
            var prPrice = cursor2.getInt(2)

            products.add(Product(prId,prTitle,prPrice))
        }

        for(eachProduct in products){
            Log.e("tag", "${eachProduct.id} -- ${eachProduct.title} -- ${eachProduct.price}")
        }
    }
}