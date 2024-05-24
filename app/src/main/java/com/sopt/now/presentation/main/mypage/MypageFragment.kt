package com.sopt.now.presentation.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt.now.databinding.FragmentMypageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MypageFragment : Fragment() {
    private val viewmodel by viewModels<MyPageViewModel>()
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.getSavedUserId()
        UserInfoObserver()
        initLogoutBtnClickListener()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun UserInfoObserver() {
        viewmodel.userInfo.observe(viewLifecycleOwner) {
            if (viewmodel.userInfo.value?.authenticationId?.isEmpty() != true) {
                binding.tvUserId.text = viewmodel.userInfo.value?.authenticationId
                binding.tvUserNickname.text = viewmodel.userInfo.value?.nickname
                binding.tvUserPhoneNumber.text = viewmodel.userInfo.value?.phone
            }
        }
    }

    private fun initLogoutBtnClickListener() {
        binding.btLogout.setOnClickListener {
            viewmodel.logOut()
        }
    }
}