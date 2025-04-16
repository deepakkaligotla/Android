package `in`.kaligotla.datastructures.presentation.main.myDataStructures.myHashTable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.datastructures.data.domain.Entry
import javax.inject.Inject

@HiltViewModel
class MyHashTableViewModel @Inject constructor() : ViewModel() {
    var table by mutableStateOf(emptyArray<Entry?>())
    var items by mutableStateOf(emptyList<Entry?>().toMutableList())

    fun createHashTable(size: Int) {
        table = Array<Entry?>(size) { null }
    }

    private fun hash(key: Int): Int {
        return key % table.size
    }

    private fun rehash(cur: Int): Int {
        return (cur + 1) % table.size
    }

    private fun checkIfTableIsFull(): Throwable? {
        if (!table.contains(null)) {
            return Exception()
        }
        return null
    }

    private fun itemsList() {
        items.clear()
        for (item in table) {
            if (item != null) {
                items.add(item)
            }
        }
    }

    fun put(key: Int, value: String) {
        var index = hash(key)
        while (table[index] != null) {
            if (table[index]?.key == key) {
                table[index]?.value = value
                return
            } else throw checkIfTableIsFull()!!
            index = rehash(index)
        }
        if (table[index] == null) {
            val temp = Entry(key, value)
            table[index] = temp
        }
        itemsList()
    }


    fun getValue(key: Int) {
        var index = hash(key)
        while (table[index] != null) {
            if (table[index]!!.key === key) {
                items.clear()
                items.add(table[index])
            }
            index = rehash(index)
        }
    }
}