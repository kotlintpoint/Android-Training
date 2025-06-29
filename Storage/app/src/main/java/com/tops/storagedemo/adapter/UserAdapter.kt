package com.tops.storagedemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tops.storagedemo.databinding.UserRowItemBinding
import com.tops.storagedemo.entities.User

class UserAdapter(private val users: List<User>, private val listener: OnUserClickListener) : Adapter<UserAdapter.UserViewHolder>() {

    interface OnUserClickListener {
        fun onUserEdit(user: User)
        fun onUserDelete(user: User)
    }

    class UserViewHolder(val binding: UserRowItemBinding) : ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.tvUser.text = "${users[position].firstName} ${users[position].lastName}"

        holder.binding.btnEdit.setOnClickListener { listener.onUserEdit(users[position]) }
        holder.binding.btnDelete.setOnClickListener { listener.onUserDelete(users[position]) }
    }
}