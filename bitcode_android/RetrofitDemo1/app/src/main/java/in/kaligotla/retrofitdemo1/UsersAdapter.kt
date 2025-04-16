package `in`.kaligotla.retrofitdemo1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import `in`.kaligotla.retrofitdemo1.databinding.UsersViewHolderBinding

class UsersAdapter(private var users: ArrayList<User>): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var usersViewHolderBinding = UsersViewHolderBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UsersViewHolderBinding.inflate(LayoutInflater.from(parent.context)).root)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(users[position].avatar).into(holder.usersViewHolderBinding.userAvatar);
        holder.usersViewHolderBinding.userFirstName.text = users[position].firstName
        holder.usersViewHolderBinding.userLastName.text = users[position].lastName
        holder.usersViewHolderBinding.userEmail.text = users[position].email
    }

    override fun getItemCount(): Int {
        return users.size
    }
}