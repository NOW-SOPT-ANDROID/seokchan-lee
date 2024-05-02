package com.sopt.now.presentation.main.friends

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sopt.now.R
import com.sopt.now.data.database.FriendDatabase
import com.sopt.now.data.model.MyProfile
import com.sopt.now.databinding.FragmentFriendBinding
import com.sopt.now.presentation.main.friends.addfriend.AddFriendActivity


class MainFragment : Fragment() {
    private val viewModel by viewModels<FriendViewModel> {
        FriendViewModelFactory(
            FriendDatabase.getDatabase(
                requireContext()
            )
        )
    }
    lateinit var myProfileAdapter: MyProfileAdapter
    lateinit var friendAdapter: FriendAdapter
    private var _binding: FragmentFriendBinding? = null
    private val binding: FragmentFriendBinding
        get() = requireNotNull(_binding)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFriendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFloatingBtnClickListener()
        initAdapter()
        viewModel.getFriend()
        FriendListObserver()

        //TODO 고쳐야됨
        val dummyProfile = mutableListOf<MyProfile>(
            MyProfile(
                profileImage = R.drawable.baseline_person_24,
                name = "이석찬",
                otherPerson = R.drawable.baseline_person_24
            )
        )
        myProfileAdapter.submitList(dummyProfile)
        //TODO 고쳐야됨

    }

    override fun onResume() {
        super.onResume()
        viewModel.getFriend()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        myProfileAdapter = MyProfileAdapter()
        friendAdapter = FriendAdapter(
            onClicked = {
                Toast.makeText(requireContext(), "클릭", Toast.LENGTH_SHORT).show()
            },
            onLongClicked = {
                makeFriendDialog(it.id)
                Toast.makeText(requireContext(), "롱클릭", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun initFloatingBtnClickListener() {
        binding.fbAddFriend.setOnClickListener {
            Intent(requireContext(), AddFriendActivity::class.java).let {
                startActivity(it)
            }
        }
    }

    private fun makeFriendDialog(id: Int?) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("친구 목록에서 삭제하시겠습니까?")
            .setNegativeButton("Cancel") { dialog: DialogInterface, which: Int ->
            }
            .setPositiveButton("Delete") { dialog: DialogInterface, which: Int ->
                deleteFriend(id)
            }.show()
    }

    private fun deleteFriend(index: Int?) {
        if (index != null) {
            runCatching { viewModel.deleteFriend(index) }
                .onSuccess {
                    viewModel.getFriend()
                }
        } else {
            Toast.makeText(requireContext(), "없는아이디", Toast.LENGTH_SHORT).show()
        }
    }

    private fun FriendListObserver() {
        viewModel.friendList.observe(viewLifecycleOwner) {
            if (viewModel.friendList.value.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "데이터없음", Toast.LENGTH_SHORT).show()
            } else {
                friendAdapter.submitList(viewModel.friendList.value)
                submitFriendList()
            }
        }
    }

    private fun submitFriendList() {
        binding.rvMain.run {
            adapter = ConcatAdapter(myProfileAdapter, friendAdapter)
        }
    }
}