package com.sopt.now.compose.screen.home.Search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.sopt.now.compose.screen.model.ReqresUser

@Composable
fun SearchScreen(
    viewmodel: SearchViewModel,
) {
    val Reqres = mutableListOf<ReqresUser>()
    viewmodel.getReqresMember()

    when (viewmodel.searchPageState.observeAsState().value) {
        SearchPageState.Empty -> {}
        is SearchPageState.Failure -> {
        }

        is SearchPageState.Success -> {
            viewmodel.reqresInfo.value?.forEach { data ->
                Reqres.add(ReqresUser(data.first_name, data.email, data.avatar))
            }
        }

        null -> TODO()
    }

    LazyColumn {
        items(Reqres) {
            SearchListScreen(it)
        }
    }
}


