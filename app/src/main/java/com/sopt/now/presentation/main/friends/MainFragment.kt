package com.sopt.now.presentation.main.friends

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    val myProfileAdapter = MyProfileAdapter()
    val friendAdapter = FriendAdapter(
        onClicked = {
            Toast.makeText(requireContext(), "클릭", Toast.LENGTH_SHORT).show()
        },
        onLongClicked = {
            makeFriendDialog()
            Toast.makeText(requireContext(), "롱클릭", Toast.LENGTH_SHORT).show()
        }
    )

    //Todo. 더미데이터옮기기
    private var dummyProfile = mutableListOf<MyProfile>(
        MyProfile(
            profileImage = R.drawable.baseline_person_24,
            name = "이석찬",
            otherPerson = R.drawable.baseline_person_24
        )
    )
    private var mockFriendList = mutableListOf<Friend>(
        Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "이의경",
            music = "한국",
        ),
        Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "우상욱",
            music = "한국",
        ),
        Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "배지현",
            music = "한국",
        ), Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "이의경",
            music = "한국",
        ),
        Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "우상욱",
            music = "한국",
        ),
        Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "배지현",
            music = "한국",
        ), Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "이의경",
            music = "한국",
        ),
        Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "우상욱",
            music = "한국",
        ),
        Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "배지현",
            music = "한국",
        ), Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "이의경",
            music = "한국",
        ),
        Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "우상욱",
            music = "한국",
        ),
        Friend(
            profileImage = R.drawable.baseline_person_24,
            name = "배지현",
            music = "한국",
        )
    )
    //Todo. 더미데이터옮기기


    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFloatingBtnClickListener()

        myProfileAdapter.submitList(dummyProfile)
        friendAdapter.submitList(mockFriendList)



        binding.rvMain.run {
            adapter = ConcatAdapter(myProfileAdapter, friendAdapter)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initFloatingBtnClickListener(){
        binding.fbAddFriend.setOnClickListener {
            Intent(requireContext(), AddFriendActivity::class.java).let {
                startActivity(it)
            }
        }
    }
    private fun makeFriendDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("친구 목록에서 삭제하시겠습니까?")
            .setNegativeButton("Cancel"){dialog:DialogInterface, w:Int ->
            }
            .setPositiveButton("Delete"){dialog:DialogInterface, w:Int ->
                deleteFriend(w)
            }.show()
    }
    private fun deleteFriend(index:Int){
        mockFriendList.removeAt(3)
        myProfileAdapter.submitList(dummyProfile)
        friendAdapter.submitList(mockFriendList)

        binding.rvMain.run {
            adapter = ConcatAdapter(myProfileAdapter, friendAdapter)
            layoutManager = LinearLayoutManager(requireContext())
        }

    }
}