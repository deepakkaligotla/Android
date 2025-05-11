package `in`.kaligotla.mvvmdemo1.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.mvvmdemo1.adapters.CourseAdapter
import `in`.kaligotla.mvvmdemo1.adapters.PostAdapter
import `in`.kaligotla.mvvmdemo1.models.Course
import `in`.kaligotla.mvvmdemo1.models.Post

@BindingAdapter("postsList")
fun bindPosts(recyclerView: RecyclerView, posts: List<Post>?) {
    if (recyclerView.adapter is PostAdapter && posts != null) {
        (recyclerView.adapter as PostAdapter).submitList(posts)
    }
}

@BindingAdapter("coursesList")
fun bindCourses(recyclerView: RecyclerView, courses: List<Course>?) {
    if (recyclerView.adapter is CourseAdapter && courses != null) {
        (recyclerView.adapter as CourseAdapter).submitList(courses)
    }
}