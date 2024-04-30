package com.sopt.now.presentation.main.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sopt.now.data.model.MyProfile
import com.sopt.now.databinding.ItemMyprofileBinding
import com.sopt.now.util.DiffUtilCallBack

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
        val diffUtil = DiffUtilCallBack<MyProfile>(
            onContentsTheSame = { old, new -> old.name == new.name },
            onItemsTheSame = { old, new -> old.name == new.name }
        )
    }
}
