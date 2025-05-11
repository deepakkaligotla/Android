package `in`.kaligotla.test3

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.test3.databinding.ViewHolderCommentBinding

class CommentAdapater(var comments: ArrayList<Comment>): RecyclerView.Adapter<CommentAdapater.CommentViewHolder>() {
    inner class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var viewHolderCommentBinding = ViewHolderCommentBinding.bind(itemView)
        fun bindData(comment: Comment) {
            viewHolderCommentBinding.postId.text = "Post Id: ${comment.postId}"
            viewHolderCommentBinding.userName.text = "username: ${comment.user.username}"
            viewHolderCommentBinding.fullName.text = "FullName: ${comment.user.fullName}"
            viewHolderCommentBinding.body.text = comment.body
            viewHolderCommentBinding.userName.setOnClickListener {
                val intent = Intent(itemView.context, DetailsActivity::class.java)
                intent.putExtra("selectedComment", comment)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(ViewHolderCommentBinding.inflate(LayoutInflater.from(parent.context)).root)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindData(comments[position])
    }
}