package `in`.kaligotla.datastructures.presentation.main.myDataStructures.myLinkedList.mySinglyLinkedList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.datastructures.data.domain.MyNode
import `in`.kaligotla.datastructures.data.domain.model.entities.Location
import `in`.kaligotla.datastructures.data.repository.LocationRepository
import javax.inject.Inject

@HiltViewModel
class MySinglyLinkedListViewModel @Inject constructor(
    private val repo: LocationRepository,
) : ViewModel() {

    private var head: MyNode? = null
    var items by mutableStateOf(emptyList<Location>().toMutableList())
    val locationsFromSQLite = repo.bindGetAllLocationsFromSQLite()
    var locationsList by mutableStateOf(emptyList<Location>())

    private fun checkIfLinkedListIsEmpty(): Boolean {
        return head == null
    }

    fun setupObservers(lifecycleOwner: LifecycleOwner) {
        locationsFromSQLite.observe(lifecycleOwner, Observer {
            locationsList = it
        })
    }

    fun createNodeStructureOfExistingData() {
        for (location in locationsList) {
            insertAtLast(location)
            if (location.location_id == 5000) break
        }
    }

    private fun itemsList() {
        var trav: MyNode? = head
        items.clear()
        while (trav != null) {
            trav.data?.let { items.add(it) }
            trav = trav.next
        }
    }

    fun insertAtFirst(value: Location) {
        val tempNode = MyNode(value)
        tempNode.next = head
        head = tempNode
        itemsList()
    }

    fun insertAtLast(value: Location) {
        val tempNode = MyNode(value)
        if (checkIfLinkedListIsEmpty()) {
            head = tempNode
            itemsList()
        } else {
            var trav: MyNode? = head
            while (trav!!.next != null)
                trav = trav.next
            trav.next = tempNode
            itemsList()
        }
    }

    fun insertAtPosition(value: Location, position: Int) {
        if (checkIfLinkedListIsEmpty() || position <= 1) {
            insertAtFirst(value)
            itemsList()
        } else {
            val tempNode = MyNode(value)
            var trav: MyNode? = head
            for (i in 1 until position - 1) {
                if (trav!!.next == null) break
                trav = trav.next
            }
            tempNode.next = trav!!.next
            trav.next = tempNode
            itemsList()
        }
    }

    fun deleteFirstNode() {
        if (!checkIfLinkedListIsEmpty())
            head = head!!.next
        itemsList()
    }

    fun delAllNodes() {
        head = null
        itemsList()
    }

    fun deleteLastNode() {
        if (checkIfLinkedListIsEmpty()) return
        if (head!!.next == null) head = null else {
            var trav: MyNode = head as MyNode
            var prev: MyNode? = null
            while (trav.next != null) {
                prev = trav
                trav = trav.next!!
            }
            prev!!.next = null
        }
        itemsList()
    }

    fun deleteNodeAtPos(pos: Int) {
        if (checkIfLinkedListIsEmpty()) return
        if (pos < 1) return
        if (pos == 1) deleteFirstNode() else {
            var prev: MyNode? = null
            var trav: MyNode? = head
            for (i in 1 until pos) {
                prev = trav
                trav = trav!!.next
                if (trav == null) return
            }
            prev!!.next = trav!!.next
        }
        itemsList()
    }
}