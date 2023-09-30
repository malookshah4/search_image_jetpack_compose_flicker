package com.flicker.searchimage.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.flicker.searchimage.network.ApiService
import com.flicker.searchimage.network.PhotoX
import com.flicker.searchimage.paging.PhotoDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    private val TAG = "MyViewModel"
    var photoList: Flow<PagingData<PhotoX>> = flowOf(PagingData.empty())
    var loadingBottom = mutableStateOf(false)

    val sharedPhoto = mutableStateOf<PhotoX?>(null)
    var searchText = mutableStateOf("")

    fun onEvent(myEvents: MyEvents) {
        when (myEvents) {
            is MyEvents.OnSearchClick -> {
                Log.d(TAG, "onEvent: called")
                viewModelScope.launch {
                    try {
                        photoList = loadMorePhotos().cachedIn(viewModelScope)
                        Log.d(TAG, "onEvent: ddddcalled$photoList")
                    } catch (e: Exception) {
                        // Handle error
                        Log.d(TAG, "onEvent: errror${e.message}")
                    }
                }
            }

            else -> {}
        }
    }

    private fun loadMorePhotos() = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 25),
        pagingSourceFactory = { PhotoDataSource(api, searchText.value,loadingBottom) }
    ).flow
}


//    fun loadMorePhotos(searchText: String) {
//        currentPage++
//        Log.d("TAG", "loadMorePhotos: called page : $currentPage")
//        viewModelScope.launch {
//            val response = api.searchPhotos(text = searchText, page = currentPage)
//            if (response.isSuccessful){
//                response.body()?.photos?.photo?.let {
//                    Log.d(TAG, "loadMorePhotos: it ${it.size}")
//                    delay(2000)
//                    photoList.value.addAll(it)
//                    Log.d(TAG, "loadMorePhotos: ${photoList.value.size}")
//                }
//            }
//        }
//    }



