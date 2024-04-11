package com.sopt.now.presentation.main.friends

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sopt.now.R
import com.sopt.now.data.database.FriendDatabase
import com.sopt.now.data.model.FriendEntity
import com.sopt.now.databinding.FragmentFriendBinding
import com.sopt.now.presentation.main.friends.addfriend.AddFriendActivity
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    val myProfileAdapter = MyProfileAdapter()
    val friendAdapter = FriendAdapter(
        onClicked = {
            Toast.makeText(requireContext(), "클릭", Toast.LENGTH_SHORT).show()
        },
        onLongClicked = {
            makeFriendDialog(it.id)
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
    //Todo

    private var _binding: FragmentFriendBinding? = null
    private val binding: FragmentFriendBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFloatingBtnClickListener()

        //TODO 데이터받아와서 리스트추가
        lifecycleScope.launch {
            val db = Room.databaseBuilder(
                requireContext(),
                FriendDatabase::class.java, "friend_database"
            ).build()
            val friendDao = db.friendDao()
            val itemlsit = FriendEntity.toFriend(friendDao.getFriend())
            friendAdapter.submitList(itemlsit)

        }
        myProfileAdapter.submitList(dummyProfile)
        //TODO


        binding.rvMain.run {
            adapter = ConcatAdapter(myProfileAdapter, friendAdapter)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        //Todo. 데이터베이스에서 삭제
        if (index != null) {
            lifecycleScope.launch {
                val db = Room.databaseBuilder(
                    requireContext(),
                    FriendDatabase::class.java, "friend_database"
                ).build()
                db.friendDao().deleteFriend(index)
            }
        } else {
            Toast.makeText(requireContext(), "없는아이디", Toast.LENGTH_SHORT).show()
        }
        //TODO
        myProfileAdapter.submitList(dummyProfile)

        binding.rvMain.run {
            adapter = ConcatAdapter(myProfileAdapter, friendAdapter)
            layoutManager = LinearLayoutManager(requireContext())
        }

    }
}