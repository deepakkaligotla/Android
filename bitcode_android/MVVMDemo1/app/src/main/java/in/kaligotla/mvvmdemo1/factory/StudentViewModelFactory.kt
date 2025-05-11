package `in`.kaligotla.mvvmdemo1.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import `in`.kaligotla.mvvmdemo1.repository.StudentRepository
import `in`.kaligotla.mvvmdemo1.viewmodels.StudentViewModel

class StudentViewModelFactory(
    private val studentRepository: StudentRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudentViewModel(studentRepository) as T
    }
}