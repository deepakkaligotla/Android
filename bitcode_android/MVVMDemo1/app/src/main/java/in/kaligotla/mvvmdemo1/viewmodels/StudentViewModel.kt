package `in`.kaligotla.mvvmdemo1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.kaligotla.mvvmdemo1.database.relationships.CourseWithStudents
import `in`.kaligotla.mvvmdemo1.database.relationships.StudentWithCourses
import `in`.kaligotla.mvvmdemo1.repository.StudentRepository
import kotlinx.coroutines.launch


class StudentViewModel(private val studentRepository: StudentRepository) : ViewModel() {

    private val _studentWithCourses = MutableLiveData<StudentWithCourses>()
    val studentWithCourses: LiveData<StudentWithCourses> = _studentWithCourses

    private val _courseWithStudents = MutableLiveData<CourseWithStudents>()
    val courseWithStudents: LiveData<CourseWithStudents> = _courseWithStudents

    fun loadStudentWithCourses(studentId: Int) {
        viewModelScope.launch {
            studentRepository.fetchStudentWithCourses()
            _studentWithCourses.postValue(studentRepository.getStudentWithCourses(studentId))
        }
    }

    fun loadCourseWithStudents(courseId: Int) {
        viewModelScope.launch {
            _courseWithStudents.postValue(studentRepository.getCourseWithStudents(courseId))
        }
    }
}