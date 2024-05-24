package com.sopt.now.presentation.main.search

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.now.databinding.ItemReqresUserBinding
import com.sopt.now.presentation.model.ReqresUser

class SearchViewHolder(
    private val binding: ItemReqresUserBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(reqresUser: ReqresUser) {
        binding.run {
            tvName.text = reqresUser.name
            tvEmail.text = reqresUser.email
            Glide.with(ivAvatar)
                .load(reqresUser.avatar)
                .into(ivAvatar)
        }
    }
}