package com.sopt.now.presentation.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.MyApplication
import com.sopt.now.databinding.FragmentMypageBinding
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.presentation.main.friends.FriendAdapter
import com.sopt.now.presentation.main.friends.MyProfileAdapter

class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLogoutBtnClickListener()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initLogoutBtnClickListener() {
        binding.btLogout.setOnClickListener {
            MyApplication.userdata.clearUserData()
        }
    }
}