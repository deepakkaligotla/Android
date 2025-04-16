package `in`.kaligotla.datastructures.presentation.main.myDataStructures.myQueue

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.datastructures.data.domain.Entry
import `in`.kaligotla.datastructures.data.domain.MyIntNode
import javax.inject.Inject

@HiltViewModel
class MyQueueViewModel @Inject constructor() : ViewModel() {
    private var head: MyIntNode? = null
    private var tail: MyIntNode? = null
    var items by mutableStateOf(emptyList<Entry>().toMutableList())

    fun Deque() {
        head = null
        tail = null
        itemQueue()
    }

    fun checkIfQueueEmpty(): Boolean {
        return head == null
    }

    private fun itemQueue() {
        if (!checkIfQueueEmpty()) {
            var trav: MyIntNode? = head
            items.clear()
            while (trav != null) {
                trav.data.let { items.add(it) }
                trav = trav.next
            }
        } else items.clear()
    }

    fun itemReverseQueue() {
        var trav: MyIntNode? = tail
        items.clear()
        while (trav != null) {
            trav.data.let { items.add(it) }
            trav = trav.prev
        }
    }

    fun addFirst(value: Entry) {
        val tempNode = MyIntNode(value)
        if (checkIfQueueEmpty()) {
            head = tempNode
            tail = tempNode
        } else {
            tempNode.next = head
            head!!.prev = tempNode
            head = tempNode
        }
        itemQueue()
    }


    fun addLast(value: Entry) {
        val tempNode = MyIntNode(value)
        if (checkIfQueueEmpty()) {
            head = tempNode
            tail = tempNode
        } else {
            tempNode.prev = tail
            tail!!.next = tempNode
            tail = tempNode
        }
        itemQueue()
    }

    fun delFirst() {
        if (checkIfQueueEmpty())
            head = null

        if (head!!.next == null) {
            head = null
            tail = null
        } else {
            head = head!!.next
            head!!.prev = null
        }
        itemQueue()
    }

    fun delLast() {
        if (checkIfQueueEmpty())
            head = null

        if (head!!.next == null) {
            head = null
            tail = null
        } else {
            tail = tail!!.prev
            tail!!.next = null
        }
        itemQueue()
    }

    fun delAll() {
        head = null
        itemQueue()
    }
}