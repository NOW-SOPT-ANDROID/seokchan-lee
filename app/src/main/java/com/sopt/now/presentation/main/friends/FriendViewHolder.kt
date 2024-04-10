package com.sopt.now.presentation.main.friends

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding

class FriendViewHolder(
    private val binding: ItemFriendBinding,
    private val onClicked: (Friend) -> Unit,
    private val onLongClicked: (Friend) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: Friend) {
        binding.run {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
            tvMusic.text = friendData.music

            initRecyclerViewClickListener(friendData)
            initRecyclerViewLongClickListener(friendData)
        }
    }
    private fun initRecyclerViewClickListener(friendData: Friend) {
        binding.root.setOnClickListener {
            onClicked(friendData)
        }
    }

    private fun initRecyclerViewLongClickListener(friendData: Friend) {
        binding.root.setOnLongClickListener {
            onLongClicked(friendData)
            return@setOnLongClickListener true
        }
    }


}