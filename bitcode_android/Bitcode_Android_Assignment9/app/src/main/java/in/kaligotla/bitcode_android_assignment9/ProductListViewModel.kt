package `in`.kaligotla.bitcode_android_assignment9

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductListViewModel : ViewModel() {
    private val repository = ProductRepository()
    private val _searchQuery = MutableStateFlow("")
    private val allProducts = MutableStateFlow<List<Product>>(emptyList())

    val filteredProducts: StateFlow<List<Product>> = combine(
        _searchQuery,
        allProducts
    ) { query, products ->
        if (query.isBlank()) {
            products
        } else {
            products.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val products = repository.getProducts()
            allProducts.value = products
        }
    }

    fun search(query: String) {
        _searchQuery.value = query
    }
}