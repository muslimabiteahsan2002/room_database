package com.example.myapplicationsqlite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationsqlite.databinding.ItemUserBinding
import com.example.myapplicationsqlite.room.User

class UserAdapter(var listener: UserClickListener): ListAdapter<User, UserAdapter.UserViewHolder>(comparator) {
    class UserViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        var comparator = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.userId == newItem.userId
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position).let {
            with(holder.binding) {
                nameTv.text = it.name
                ageTv.text = "${it.age}"
                mobileTv.text = it.mobile
                editBtn.setOnClickListener { _ ->
                    listener.userUpdate(it)
                }
                deleteBtn.setOnClickListener { _->
                    listener.userDelete(it)
                }
            }
        }
    }
}