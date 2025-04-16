package `in`.kaligotla.datastructures.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import `in`.kaligotla.datastructures.navigation.Screen.*
import `in`.kaligotla.datastructures.presentation.NavRoutes
import `in`.kaligotla.datastructures.presentation.main.myDataStructures.myHashTable.MyHashTable
import `in`.kaligotla.datastructures.presentation.main.myDataStructures.myLinkedList.MyLinkedList
import `in`.kaligotla.datastructures.presentation.main.myDataStructures.myLinkedList.myCircularLinkedList.MyCircularLinkedList
import `in`.kaligotla.datastructures.presentation.main.myDataStructures.myLinkedList.myDoublyLinkedList.MyDoublyLinkedList
import `in`.kaligotla.datastructures.presentation.main.myDataStructures.myLinkedList.mySinglyLinkedList.MySinglyLinkedList
import `in`.kaligotla.datastructures.presentation.main.myDataStructures.myQueue.MyQueue
import `in`.kaligotla.datastructures.presentation.main.myDataStructures.myStack.MyStack
import `in`.kaligotla.datastructures.presentation.main.myDataStructures.myVector.MyVector
import `in`.kaligotla.datastructures.presentation.main.mySearch.myBinarySearch.MyBinarySearch
import `in`.kaligotla.datastructures.presentation.main.mySearch.myBreadthFirstSearch.MyBreadthFirstSearch
import `in`.kaligotla.datastructures.presentation.main.mySearch.myDepthFirstSearch.MyDepthFirstSearch
import `in`.kaligotla.datastructures.presentation.main.mySearch.myExponentialSearch.MyExponentialSearch
import `in`.kaligotla.datastructures.presentation.main.mySearch.myInterpolationSearch.MyInterpolationSearch
import `in`.kaligotla.datastructures.presentation.main.mySearch.myJumpSearch.MyJumpSearch
import `in`.kaligotla.datastructures.presentation.main.mySearch.myLinearSearch.MyLinearSearch
import `in`.kaligotla.datastructures.presentation.main.mySearch.mySequentialSearch.MySequentialSearch
import `in`.kaligotla.datastructures.presentation.main.mySort.myBubbleSort.MyBubbleSort
import `in`.kaligotla.datastructures.presentation.main.mySort.myHeapSort.MyHeapSort
import `in`.kaligotla.datastructures.presentation.main.mySort.myInsertionSort.MyInsertionSort
import `in`.kaligotla.datastructures.presentation.main.mySort.myMergeSort.MyMergeSort
import `in`.kaligotla.datastructures.presentation.main.mySort.myQuickSort.MyQuickSort
import `in`.kaligotla.datastructures.presentation.main.mySort.mySelectionSort.MySelectionSort
import `in`.kaligotla.datastructures.presentation.main.mySort.myShellSort.MyShellSort

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.subGraph(navController: NavHostController, drawerState: DrawerState) {
    navigation(startDestination = MainNavOption.MyTable.name, route = NavRoutes.SubRoute.name) {
        composable(SubNavOption.MyVector.name) {
            MyVector(drawerState)
        }

        composable(SubNavOption.MyLinkedList.name) {
            MyLinkedList(drawerState, navController)
        }

        composable(SubNavOption.MySinglyLinkedList.name) {
            MySinglyLinkedList(drawerState)
        }

        composable(SubNavOption.MyDoublyLinkedList.name) {
            MyDoublyLinkedList(drawerState)
        }

        composable(SubNavOption.MyCircularLinkedList.name) {
            MyCircularLinkedList(drawerState)
        }

        composable(SubNavOption.MyQueue.name) {
            MyQueue(drawerState)
        }

        composable(SubNavOption.MyStack.name) {
            MyStack(drawerState)
        }

        composable(SubNavOption.MyHashTable.name) {
            MyHashTable(drawerState)
        }

        composable(SubNavOption.MyBinarySearch.name) {
            MyBinarySearch(drawerState)
        }

        composable(SubNavOption.MyBreadthFirstSearch.name) {
            MyBreadthFirstSearch(drawerState)
        }

        composable(SubNavOption.MyDepthFirstSearch.name) {
            MyDepthFirstSearch(drawerState)
        }

        composable(SubNavOption.MyExponentialSearch.name) {
            MyExponentialSearch(drawerState)
        }

        composable(SubNavOption.MyInterpolationSearch.name) {
            MyInterpolationSearch(drawerState)
        }

        composable(SubNavOption.MyJumpSearch.name) {
            MyJumpSearch(drawerState)
        }

        composable(SubNavOption.MyLinearSearch.name) {
            MyLinearSearch(drawerState)
        }

        composable(SubNavOption.MySequentialSearch.name) {
            MySequentialSearch(drawerState)
        }

        composable(SubNavOption.MySelectionSort.name) {
            MySelectionSort(drawerState)
        }

        composable(SubNavOption.MyBubbleSort.name) {
            MyBubbleSort(drawerState)
        }

        composable(SubNavOption.MyHeapSort.name) {
            MyHeapSort(drawerState)
        }

        composable(SubNavOption.MyInsertionSort.name) {
            MyInsertionSort(drawerState)
        }

        composable(SubNavOption.MyMergeSort.name) {
            MyMergeSort(drawerState)
        }

        composable(SubNavOption.MyQuickSort.name) {
            MyQuickSort(drawerState)
        }

        composable(SubNavOption.MyShellSort.name) {
            MyShellSort(drawerState)
        }
    }
}


enum class SubNavOption {
    MyVector,
    MyLinkedList,
    MySinglyLinkedList,
    MyDoublyLinkedList,
    MyCircularLinkedList,
    MyQueue,
    MyStack,
    MyHashTable,
    MyBinarySearch,
    MyBreadthFirstSearch,
    MyDepthFirstSearch,
    MyExponentialSearch,
    MyInterpolationSearch,
    MyJumpSearch,
    MyLinearSearch,
    MySequentialSearch,
    MySelectionSort,
    MyBubbleSort,
    MyHeapSort,
    MyInsertionSort,
    MyMergeSort,
    MyQuickSort,
    MyShellSort,
}