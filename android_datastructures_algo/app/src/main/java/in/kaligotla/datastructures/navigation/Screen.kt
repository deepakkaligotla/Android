package `in`.kaligotla.datastructures.navigation

import `in`.kaligotla.datastructures.core.Constants.MY_BINARY_SEARCH_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_BREADTH_FIRST_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_BUBBLE_SORT_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_CIRCULAR_LINKED_LIST_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_DATA_STRUCTURES_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_DEPTH_FIRST_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_DOUBLY_LINKED_LIST_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_EXPONENTIAL_SEARCH_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_HASH_TABLE_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_HEAP_SORT_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_INSERTION_SORT_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_INTERPOLATION_SEARCH_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_JUMP_SEARCH_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_LINEAR_SEARCH_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_LINKED_LIST_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_MERGE_SORT_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_QUEUE_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_QUICK_SORT_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_SEARCH_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_SELECTION_SORT_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_SEQUENTIAL_SEARCH_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_SHELL_SORT_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_SINGLY_LINKED_LIST_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_SORT_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_STACK_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_TABLE_SCREEN
import `in`.kaligotla.datastructures.core.Constants.MY_VECTOR_SCREEN

sealed class Screen(val route: String) {

    object MyTableScreen : Screen(MY_TABLE_SCREEN)
    object MyDataStructuresScreen : Screen(MY_DATA_STRUCTURES_SCREEN)
    object MyLinkedListScreen : Screen(MY_LINKED_LIST_SCREEN)
    object MySearchScreen : Screen(MY_SEARCH_SCREEN)
    object MySortScreen : Screen(MY_SORT_SCREEN)

    //Data Structures
    object MySinglyLinkedListScreen : Screen(MY_SINGLY_LINKED_LIST_SCREEN)
    object MyDoublyLinkedListScreen : Screen(MY_DOUBLY_LINKED_LIST_SCREEN)
    object MyQueueScreen : Screen(MY_QUEUE_SCREEN)
    object MyCircularLinkedListScreen : Screen(MY_CIRCULAR_LINKED_LIST_SCREEN)
    object MyVectorScreen : Screen(MY_VECTOR_SCREEN)
    object MyHashTableScreen : Screen(MY_HASH_TABLE_SCREEN)
    object MyStackScreen : Screen(MY_STACK_SCREEN)

    //Search
    object MyBinarySearchScreen : Screen(MY_BINARY_SEARCH_SCREEN)
    object MyBreadthFirstSearch : Screen(MY_BREADTH_FIRST_SCREEN)
    object MyDepthFirstSearch : Screen(MY_DEPTH_FIRST_SCREEN)
    object MyExponentialSearchScreen : Screen(MY_EXPONENTIAL_SEARCH_SCREEN)
    object MyInterpolationSearchScreen : Screen(MY_INTERPOLATION_SEARCH_SCREEN)
    object MyJumpSearchScreen : Screen(MY_JUMP_SEARCH_SCREEN)
    object MyLinearSearchScreen : Screen(MY_LINEAR_SEARCH_SCREEN)
    object MySequentialSearchScreen : Screen(MY_SEQUENTIAL_SEARCH_SCREEN)

    //Sort
    object MyBubbleSortScreen : Screen(MY_BUBBLE_SORT_SCREEN)
    object MyHeapSortScreen : Screen(MY_HEAP_SORT_SCREEN)
    object MyInsertionSortScreen : Screen(MY_INSERTION_SORT_SCREEN)
    object MyMergeSortScreen : Screen(MY_MERGE_SORT_SCREEN)
    object MyQuickSortScreen : Screen(MY_QUICK_SORT_SCREEN)
    object MySelectionSortScreen : Screen(MY_SELECTION_SORT_SCREEN)
    object MyShellSortScreen : Screen(MY_SHELL_SORT_SCREEN)
}