package `in`.kaligotla.retrofitdemo3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.retrofitdemo3.databinding.CommentsViewHolderBinding

class CommentsAdapter(private var groupedComments: Map<Int, List<APIResponse>>): RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    private var postIds: List<Int> = groupedComments.keys.toList()

    fun setComments(newComments: List<APIResponse>) {
        groupedComments = newComments.groupBy { it.post_id }
        postIds = groupedComments.keys.toList()
        notifyDataSetChanged()
    }

    inner class CommentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var commentsViewHolderBinding = CommentsViewHolderBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(CommentsViewHolderBinding.inflate(LayoutInflater.from(parent.context)).root)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val postId = postIds[position]
        val comments = groupedComments[postId] ?: emptyList()
        holder.commentsViewHolderBinding.postID.text = postId.toString()
        for(comment in comments) {
            holder.commentsViewHolderBinding.commentID.text = comment.id.toString()
        }
    }

    override fun getItemCount(): Int {
        return postIds.size
    }
}