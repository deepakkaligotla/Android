package `in`.kaligotla.mvvmdemo1.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import `in`.kaligotla.mvvmdemo1.database.relationships.CourseWithStudents
import `in`.kaligotla.mvvmdemo1.database.relationships.StudentWithCourses
import `in`.kaligotla.mvvmdemo1.models.Course
import `in`.kaligotla.mvvmdemo1.models.Student
import `in`.kaligotla.mvvmdemo1.models.StudentCourseCrossRef

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Course)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentCourseCrossRef(ref: StudentCourseCrossRef)

    @Transaction
    @Query("SELECT * FROM Student WHERE studentId = :studentId")
    suspend fun getStudentWithCourses(studentId: Int): StudentWithCourses?

    @Transaction
    @Query("SELECT * FROM Course WHERE courseId = :courseId")
    suspend fun getCourseWithStudents(courseId: Int): CourseWithStudents?
}