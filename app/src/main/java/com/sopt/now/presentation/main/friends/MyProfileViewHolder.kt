package com.sopt.now.presentation.main.friends

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.model.local.MyProfile
import com.sopt.now.databinding.ItemMyprofileBinding

class MyProfileViewHolder(private val binding: ItemMyprofileBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(myProfile: MyProfile) {
        binding.run {
            ivMyProfile.setImageResource(myProfile.profileImage)
            tvMyName.text = myProfile.name
            ivOtherPerson.setImageResource(myProfile.otherPerson)
        }
    }
}