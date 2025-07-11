package com.tops.viewmodeldemo

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingConversion
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tops.viewmodeldemo.databinding.UserRowItemBinding
import com.tops.viewmodeldemo.model.User

@BindingConversion
fun booleanToVisibility(email: String): Int {
    return if (email != "") View.VISIBLE else View.GONE
}

class UserAdapter(val users: List<User>) : Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder( val binding : UserRowItemBinding) : ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
//        var binding = UserRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        var binding:UserRowItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_row_item,parent,false)

        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        val user = users[position]
//        holder.binding.tvUserName.setText(user.name)
        holder.binding.user = users[position]
    }
}