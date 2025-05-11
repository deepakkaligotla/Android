package `in`.kaligotla.mvvmdemo1.database.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import `in`.kaligotla.mvvmdemo1.models.Course
import `in`.kaligotla.mvvmdemo1.models.Student
import `in`.kaligotla.mvvmdemo1.models.StudentCourseCrossRef

data class CourseWithStudents(
    @Embedded val course: Course,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentId",
        associateBy = Junction(StudentCourseCrossRef::class)
    )
    val students: List<Student>
)
