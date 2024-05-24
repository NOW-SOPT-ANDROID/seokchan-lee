package com.sopt.now.presentation.main.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt.now.data.ServicePool
import com.sopt.now.data.model.remote.response.Data
import com.sopt.now.data.model.remote.response.ResponseReqresDto
import com.sopt.now.databinding.FragmentSearchBinding
import com.sopt.now.presentation.model.ReqresUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = requireNotNull(_binding)
    private val reqresService by lazy { ServicePool.reqresService }
    val reqresAdapter = SearchAdapter()
    val itemList = mutableListOf<ReqresUser>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getReqresMember()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getReqresMember() {
        reqresService.getReqresPage(2).enqueue(object : Callback<ResponseReqresDto> {
            override fun onResponse(
                call: Call<ResponseReqresDto>,
                response: Response<ResponseReqresDto>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    initSearchFragmentView(data)

                } else {
                    Log.d("reqres", response.errorBody()?.string()!!)
                }
            }

            override fun onFailure(call: Call<ResponseReqresDto>, t: Throwable) {
                if (t is HttpException) {
                    Log.e("dd", t.message().toString())
                } else {
                    Log.e("dd", t.toString())
                }
            }
        })
    }

    private fun initSearchFragmentView(data: List<Data>?) {
        data?.forEach { data ->
            itemList.add(ReqresUser(data.first_name, data.email, data.avatar))
        }
        reqresAdapter.submitList(itemList)
        binding.rvMain.adapter = reqresAdapter

    }
}