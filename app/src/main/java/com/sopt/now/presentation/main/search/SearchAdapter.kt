package com.sopt.now.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sopt.now.databinding.ItemReqresUserBinding
import com.sopt.now.presentation.model.ReqresUser
import com.sopt.now.util.DiffUtilCallBack

class SearchAdapter : ListAdapter<ReqresUser, SearchViewHolder>(diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemReqresUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    companion object {
        val diffUtil = DiffUtilCallBack<ReqresUser>(
            onContentsTheSame = { old, new -> old.name == new.name },
            onItemsTheSame = { old, new -> old.name == new.name }
        )
    }
}