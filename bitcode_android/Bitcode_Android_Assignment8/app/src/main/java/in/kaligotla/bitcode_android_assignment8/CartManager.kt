package `in`.kaligotla.bitcode_android_assignment8

object CartManager {
    val cartItems = mutableListOf<Product>()
    fun addToCart(product: Product) {
        cartItems.add(product)
    }
    fun getMyCartItems(): List<Product> = cartItems
    fun getTotalPrice(): Double = cartItems.sumOf { it.price }
    fun size(): Int = cartItems.size
    fun clearCart() {
        cartItems.clear()
    }
}