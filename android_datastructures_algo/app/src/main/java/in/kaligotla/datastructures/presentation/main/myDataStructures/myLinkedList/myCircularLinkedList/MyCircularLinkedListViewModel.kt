package `in`.kaligotla.datastructures.presentation.main.myDataStructures.myLinkedList.myCircularLinkedList

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
class MyCircularLinkedListViewModel @Inject constructor(
    private val repo: LocationRepository,
) : ViewModel() {
    private var head: MyNode2? = null
    var items by mutableStateOf(emptyList<Location>().toMutableList())
    private val locationsFromSQLite = repo.bindGetAllLocationsFromSQLite()
    var locationsList by mutableStateOf(emptyList<Location>())

    fun setupObservers(lifecycleOwner: LifecycleOwner) {
        locationsFromSQLite.observe(lifecycleOwner, Observer {
            locationsList = it
        })
    }

    fun createNodeStructureOfExistingData(size: Int) {
        deleteAllJobsInCLL()
        for (location in locationsList) {
            addJobAtLast(location)
            if (location.location_id == size) break
        }
    }

    fun checkIfCircularLinkedList(): Boolean {
        return head == null
    }

    private fun itemsList() {
        items.clear()
        if (!checkIfCircularLinkedList()) {
            var trav: MyNode2? = head
            do {
                trav!!.data?.let { items.add(it) }
                trav = trav.next
            } while (trav !== head)
        }
    }

    fun addJobAtFirst(value: Location) {
        val tempJob = MyNode2(value)
        if (checkIfCircularLinkedList()) {
            head = tempJob
            tempJob.next = head
        } else {
            var trav: MyNode2? = head
            while (trav!!.next !== head) trav = trav!!.next
            tempJob.next = head
            trav!!.next = tempJob
            head = tempJob
        }
        itemsList()
    }

    fun addJobAtLast(value: Location) {
        val tempJob = MyNode2(value)
        if (checkIfCircularLinkedList()) {
            head = tempJob
            tempJob.next = head
        } else {
            var trav: MyNode2? = head
            while (trav!!.next !== head) {
                trav = trav!!.next
            }
            tempJob.next = head
            trav!!.next = tempJob
        }
        itemsList()
    }

    fun addJobAtPosition(value: Location, position: Int) {
        if (checkIfCircularLinkedList() || position <= 1) addJobAtFirst(value) else {
            val tempJob = MyNode2(value)
            var trav: MyNode2? = head
            for (i in 1 until position - 1) {
                if (trav!!.next === head) break
                trav = trav!!.next
            }
            tempJob.next = trav!!.next
            trav.next = tempJob
        }
        itemsList()
    }

    fun deleteJobAtFirst() {
        if (checkIfCircularLinkedList()) return
        if (head == head!!.next) head = null else {
            var trav: MyNode2? = head
            while (trav!!.next !== head) trav = trav!!.next
            head = head!!.next
            trav!!.next = head
        }
        itemsList()
    }

    fun deleteJobAtLast() {
        if (checkIfCircularLinkedList()) return
        if (head == head!!.next) head = null else {
            var prev: MyNode2? = null
            var trav: MyNode2? = head
            while (trav!!.next !== head) {
                prev = trav
                trav = trav!!.next
            }
            prev!!.next = head
            trav!!.next = null
        }
        itemsList()
    }

    fun deleteJobAtPosition(position: Int) {
        if (checkIfCircularLinkedList() || position < 1) return

        if (position === 1) deleteJobAtFirst() else {
            var prev: MyNode2? = null
            var trav: MyNode2? = head
            for (i in 1 until position) {
                prev = trav
                trav = trav!!.next
                if (trav === head) return
            }
            prev!!.next = trav!!.next
        }
        itemsList()
    }

    fun deleteAllJobsInCLL() {
        head = null
        itemsList()
    }
}