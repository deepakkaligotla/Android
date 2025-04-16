package `in`.kaligotla.datastructures.presentation.main.myDataStructures.myLinkedList.myDoublyLinkedList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.datastructures.data.domain.MyNode2
import `in`.kaligotla.datastructures.data.domain.model.entities.Location
import `in`.kaligotla.datastructures.data.repository.LocationRepository
import javax.inject.Inject


@HiltViewModel
class MyDoublyLinkedListViewModel @Inject constructor(
    private val repo: LocationRepository,
) : ViewModel() {
    private var head: MyNode2? = null
    var items by mutableStateOf(emptyList<Location>().toMutableList())
    private val locationsFromSQLite = repo.bindGetAllLocationsFromSQLite()
    var locationsList by mutableStateOf(emptyList<Location>())

    private fun checkIfLinkedListIsEmpty(): Boolean {
        return head == null
    }

    fun setupObservers(lifecycleOwner: LifecycleOwner) {
        locationsFromSQLite.observe(lifecycleOwner, Observer {
            locationsList = it
        })
    }

    fun createNodeStructureOfExistingData(size: Int) {
        delAllNodes()
        if (size != 0) {
            for (location in locationsList) {
                insertAtLast(location)
                if (location.location_id == size) break
            }
        } else if (size == 0) delAllNodes()
    }

    private fun itemsList() {
        var trav: MyNode2? = head
        items.clear()
        while (trav != null) {
            trav.data?.let { items.add(it) }
            trav = trav.next
        }
    }

    fun insertAtFirst(value: Location) {
        val tempNode = MyNode2(value)
        if (checkIfLinkedListIsEmpty())
            head = tempNode
        else {
            tempNode.next = head
            head!!.prev = tempNode
            head = tempNode
        }
        itemsList()
    }

    fun insertAtLast(value: Location) {
        val tempNode = MyNode2(value)
        if (checkIfLinkedListIsEmpty()) head = tempNode
        else {
            var trav: MyNode2? = head
            while (trav!!.next != null) trav = trav.next
            tempNode.prev = trav
            trav.next = tempNode
        }
        itemsList()
    }

    fun insertAtPosition(value: Location, position: Int) {
        if (checkIfLinkedListIsEmpty() || position <= 1) insertAtFirst(value) else {
            val tempNode = MyNode2(value)
            var trav: MyNode2? = head
            for (i in 1 until position - 1) {
                trav = trav!!.next
                if (trav!!.next == null) break
            }
            val temp: MyNode2? = trav!!.next
            tempNode.next = temp
            tempNode.prev = trav
            trav.next = tempNode
            if (temp != null) temp.prev = tempNode
        }
        itemsList()
    }

    fun deleteFirstNode() {
        if (checkIfLinkedListIsEmpty())
            return
        if (head!!.next == null)
            head = null
        else {
            head = head!!.next
            head!!.prev = null
        }
        itemsList()
    }

    fun delAllNodes() {
        head = null
        itemsList()
    }

    fun deleteLastNode() {
        if (checkIfLinkedListIsEmpty()) return
        if (head!!.next == null) head = null
        else {
            var trav: MyNode2 = head as MyNode2
            var prev: MyNode2? = null
            while (trav.next != null) {
                prev = trav
                trav = trav.next!!
            }
            trav.prev!!.next = null
        }
        itemsList()
    }

    fun deleteNodeAtPos(pos: Int) {
        if (checkIfLinkedListIsEmpty() || pos < 1) return
        if (pos === 1) deleteFirstNode() else {
            var trav: MyNode2? = head
            for (i in 1 until pos) {
                trav = trav!!.next
                if (trav == null) break
            }
            trav!!.prev!!.next = trav.next
            if (trav.next != null) trav.next!!.prev = trav.prev
        }
        itemsList()
    }
}