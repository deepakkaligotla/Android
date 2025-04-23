package `in`.kaligotla.bitcode_android_assignment9

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class CartItem(
    val product: Product,
    var quantity: Int
)

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<List<CartItem>>(emptyList())
    val cartItems: LiveData<List<CartItem>> = _cartItems
    private val _totalAmount = MutableLiveData<Double>(0.0)
    val totalAmount: LiveData<Double> = _totalAmount

    fun addToCart(product: Product, quantity: Int = 1) {
        val items = _cartItems.value?.toMutableList() ?: mutableListOf()
        val existingItem = items.find { it.product.id == product.id }

        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            items.add(CartItem(product, quantity))
        }

        _cartItems.value = items
        calculateTotal()
    }

    fun removeFromCart(product: Product) {
        val items = _cartItems.value?.toMutableList()
        items?.removeAll { it.product.id == product.id }
        _cartItems.value = items ?: emptyList()
        calculateTotal()
    }

    fun incrementQuantity(product: Product) {
        val items = _cartItems.value?.toMutableList() ?: return
        val item = items.find { it.product.id == product.id }
        item?.let {
            it.quantity += 1
            _cartItems.value = items
            calculateTotal()
        }
    }

    fun decrementQuantity(product: Product) {
        val items = _cartItems.value?.toMutableList() ?: return
        val item = items.find { it.product.id == product.id }
        item?.let {
            if (it.quantity > 1) {
                it.quantity -= 1
                _cartItems.value = items
                calculateTotal()
            } else {
                removeFromCart(product)
            }
        }
    }

    fun getTotalAmount(): Double {
        return _cartItems.value?.sumOf { it.product.price * it.quantity } ?: 0.0
    }

    private fun calculateTotal() {
        _totalAmount.value = getTotalAmount()
    }

    fun clearCart() {
        _cartItems.value = emptyList()
        _totalAmount.value = 0.0
    }
}