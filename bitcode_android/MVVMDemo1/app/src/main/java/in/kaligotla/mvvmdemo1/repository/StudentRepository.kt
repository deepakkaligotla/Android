package `in`.kaligotla.mvvmdemo1.repository

import `in`.kaligotla.mvvmdemo1.database.AppDatabase
import `in`.kaligotla.mvvmdemo1.models.StudentCourseCrossRef
import `in`.kaligotla.mvvmdemo1.network.NetworkService

class StudentRepository(
    private val db: AppDatabase,
    private val api: NetworkService
) {

    suspend fun fetchStudentWithCourses() {
        val studentWithCourses = api.getStudentWithCourses()

        db.getStudentDao().insertStudent(studentWithCourses.student)

        studentWithCourses.courses.forEach { course ->
            db.getStudentDao().insertCourse(course)
            db.getStudentDao().insertStudentCourseCrossRef(
                StudentCourseCrossRef(
                    studentId = studentWithCourses.student.studentId,
                    courseId = course.courseId
                )
            )
        }
    }

    suspend fun getStudentWithCourses(studentId: Int) = db.getStudentDao().getStudentWithCourses(studentId)
    suspend fun getCourseWithStudents(courseId: Int) = db.getStudentDao().getCourseWithStudents(courseId)
}