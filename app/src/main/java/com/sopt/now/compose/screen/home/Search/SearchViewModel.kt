package com.sopt.now.compose.screen.home.Search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.data.model.Data
import com.sopt.now.compose.data.model.ResponseReqresDto
import com.sopt.now.compose.data.model.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    private var _reqresInfo: MutableLiveData<List<Data>?> = MutableLiveData(listOf())
    val reqresInfo: MutableLiveData<List<Data>?> get() = _reqresInfo

    private val _searchPageState: MutableLiveData<SearchPageState> =
        MutableLiveData(SearchPageState.Empty)
    val searchPageState: LiveData<SearchPageState> get() = _searchPageState

    private val reqresService by lazy { ServicePool.reqresService }

    fun getReqresMember() {
        reqresService.getReqresPage(2).enqueue(object : Callback<ResponseReqresDto> {
            override fun onResponse(
                call: Call<ResponseReqresDto>,
                response: Response<ResponseReqresDto>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    _reqresInfo.value = data
                    _searchPageState.value = SearchPageState.Success(R.string.mypage_success)
                } else {
                    _searchPageState.value = SearchPageState.Failure(R.string.signup_failure_input)
                }
            }

            override fun onFailure(call: Call<ResponseReqresDto>, t: Throwable) {
                _searchPageState.value = SearchPageState.Failure(R.string.signup_server_error)
            }
        })
    }
}