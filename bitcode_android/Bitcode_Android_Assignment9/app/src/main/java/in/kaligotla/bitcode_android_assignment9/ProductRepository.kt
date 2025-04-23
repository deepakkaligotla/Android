package `in`.kaligotla.bitcode_android_assignment9

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class ProductRepository {
    private val productList = listOf(
        Product(1, "Red Shirt", 499.99, "A bright red cotton shirt", "https://via.placeholder.com/150/FF0000/FFFFFF"),
        Product(2, "Blue Jeans", 999.0, "Classic blue denim jeans", "https://via.placeholder.com/150/0000FF/FFFFFF"),
        Product(3, "Running Shoes", 1999.99, "Comfortable running shoes", "https://via.placeholder.com/150/00FF00/000000"),
        Product(4, "Black Hoodie", 899.50, "Stylish black hoodie", "https://via.placeholder.com/150/000000/FFFFFF"),
        Product(5, "White Sneakers", 1499.00, "Trendy white sneakers", "https://via.placeholder.com/150/FFFFFF/000000")
    )

    private val _productsFlow = MutableStateFlow(productList)
    val productsFlow: Flow<List<Product>> get() = _productsFlow

    suspend fun getProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                title = "Wireless Earbuds",
                description = "Noise-cancelling, long battery life.",
                price = 2999.0,
                imageUrl = "https://via.placeholder.com/150"
            ),
            Product(
                id = 2,
                title = "Smart Watch",
                description = "Fitness tracking and notifications.",
                price = 4999.0,
                imageUrl = "https://via.placeholder.com/150"
            ),
            Product(
                id = 3,
                title = "Bluetooth Speaker",
                description = "Portable speaker with deep bass.",
                price = 1999.0,
                imageUrl = "https://via.placeholder.com/150"
            ),
            Product(
                id = 4,
                title = "Gaming Mouse",
                description = "Ergonomic design with RGB lighting.",
                price = 1499.0,
                imageUrl = "https://via.placeholder.com/150"
            )
        )
    }

    fun searchProducts(query: String): Flow<List<Product>> {
        return _productsFlow.map { products ->
            if (query.isBlank()) {
                products
            } else {
                products.filter {
                    it.title.contains(query, ignoreCase = true) ||
                            it.description.contains(query, ignoreCase = true)
                }
            }
        }
    }
}