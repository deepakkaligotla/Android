package `in`.kaligotla.mvvmdemo1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.mvvmdemo1.databinding.ItemPostBinding
import `in`.kaligotla.mvvmdemo1.models.Post


class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private var posts: List<Post> = listOf()

    fun submitList(data: List<Post>) {
        posts = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }
    }
}