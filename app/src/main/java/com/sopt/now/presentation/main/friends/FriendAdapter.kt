package com.sopt.now.presentation.main.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sopt.now.presentation.model.Friend
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.util.DiffUtilCallBack

class FriendAdapter(
    private val onClicked: (Friend) -> Unit,
    private val onLongClicked: (Friend) -> Unit
) : ListAdapter<Friend, FriendViewHolder>(diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(
            ItemFriendBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClicked,
            onLongClicked
        )
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }


    companion object {
        val diffUtil = DiffUtilCallBack<Friend>(
            onContentsTheSame = { old, new -> old.id == new.id },
            onItemsTheSame = { old, new -> old.name == new.name }
        )
    }
}