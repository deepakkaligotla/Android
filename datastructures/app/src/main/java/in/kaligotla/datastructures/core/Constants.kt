package `in`.kaligotla.datastructures.core

object Constants {
    //App
    const val TAG = "AppTag"

    //Main Screen
    const val HOME = "Home"

    //Navigation Drawer
    const val MY_TABLE_SCREEN = "MyTable"
    const val MY_DATA_STRUCTURES_SCREEN = "MyDataStructures"
    const val MY_LINKED_LIST_SCREEN = "MyLinkedList"
    const val MY_SEARCH_SCREEN = "MySearch"
    const val MY_SORT_SCREEN = "MySort"


    //Data Structures
    const val MY_SINGLY_LINKED_LIST_SCREEN = "MySinglyLinkedList"
    const val MY_DOUBLY_LINKED_LIST_SCREEN = "MyDoublyLinkedList"
    const val MY_QUEUE_SCREEN = "MyQueue"
    const val MY_CIRCULAR_LINKED_LIST_SCREEN = "MyCircularLinkedList"
    const val MY_VECTOR_SCREEN = "MyVector"
    const val MY_HASH_TABLE_SCREEN = "MyHashTable"
    const val MY_STACK_SCREEN = "MyStack"

    //Search
    const val MY_BINARY_SEARCH_SCREEN = "MyBinarySearch"
    const val MY_BREADTH_FIRST_SCREEN = "MyBreadthFirstSearch"
    const val MY_DEPTH_FIRST_SCREEN = "MyDepthFirstSearch"
    const val MY_EXPONENTIAL_SEARCH_SCREEN = "MyExponentialSearch"
    const val MY_INTERPOLATION_SEARCH_SCREEN = "MyInterpolationSearch"
    const val MY_JUMP_SEARCH_SCREEN = "MyJumpSearch"
    const val MY_LINEAR_SEARCH_SCREEN = "MyLinearSearch"
    const val MY_SEQUENTIAL_SEARCH_SCREEN = "MySequentialSearch"

    //Sort
    const val MY_BUBBLE_SORT_SCREEN = "MyBubbleSort"
    const val MY_HEAP_SORT_SCREEN = "MyHeapSort"
    const val MY_INSERTION_SORT_SCREEN = "MyInsertionSort"
    const val MY_MERGE_SORT_SCREEN = "MyMergeSort"
    const val MY_QUICK_SORT_SCREEN = "MyQuickSort"
    const val MY_SELECTION_SORT_SCREEN = "MySelectionSort"
    const val MY_SHELL_SORT_SCREEN = "MyShellSort"

    const val EXTRAS_RETROFIT_BUILD = "retrofitBuild"
    const val LOCATION_SERVICE = "locationService"
    const val LOCATION_REMOTE_DATA_SOURCE = "locationRemoteDataSource"
    const val LOAD_DATA_SERVICE = "loadDataService"

    const val SEARCH_BY_PINCODE = "SELECT * FROM location WHERE pincode LIKE '%' || :pincode || '%'"
}