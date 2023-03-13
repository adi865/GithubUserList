package com.example.githubuserlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserlist.databinding.ListItemBinding
import com.example.githubuserlist.models.Users
import com.example.githubuserlist.models.UsersItem

class RecyclerAdapter(): RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    //--- TO DO ---
    //diffutil yet to add to pass data from viewholder via activity to model classes to recyclerview items
    private val differCallback = object: DiffUtil.ItemCallback<UsersItem>() {
        override fun areItemsTheSame(oldItem: UsersItem, newItem: UsersItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UsersItem, newItem: UsersItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.bind(user)
    }

    inner class RecyclerViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UsersItem) {
            binding.apply {
                Glide.with(binding.root).load(item.avatar_url).into(binding.rvIvAvatar)
                tvName.text = item.login
                profileLink.text = item.html_url
                tvRole.text = item.type
            }
        }
    }
}