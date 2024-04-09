package com.sopt.now.presentation.main.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMainBinding

class MainFragment : Fragment() {


    //Todo. 더미데이터옮기기
    private val dummyProfile = listOf<MyProfile>(
        MyProfile(
            profileImage = R.drawable.baseline_person_24,
            name = "이석찬",
            otherPerson = R.drawable.baseline_person_24
        )
    )
    private val mockFriendList = listOf<Friend>(
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

        val myProfileAdapter = MyProfileAdapter()
        myProfileAdapter.submitList(dummyProfile)
        val friendAdapter = FriendAdapter()
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
}