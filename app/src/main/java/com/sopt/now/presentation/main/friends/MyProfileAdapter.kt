package com.sopt.now.presentation.main.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.now.databinding.ItemMyprofileBinding

class MyProfileAdapter : ListAdapter<MyProfile, MyProfileViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProfileViewHolder {
        return MyProfileViewHolder(
            ItemMyprofileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyProfileViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MyProfile>() {
            override fun areItemsTheSame(oldItem: MyProfile, newItem: MyProfile): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: MyProfile, newItem: MyProfile): Boolean {
                return oldItem == newItem
            }
        }
    }
}