package com.flicker.searchimage.paging

import androidx.compose.runtime.MutableState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flicker.searchimage.network.ApiService
import com.flicker.searchimage.network.PhotoX
import kotlinx.coroutines.delay
import javax.inject.Inject

class PhotoDataSource @Inject constructor(
    private val apiService: ApiService,
    private val searchText: String,
    private var loadingBottom : MutableState<Boolean>
    ) : PagingSource<Int, PhotoX>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoX>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.minus(1)?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoX> {
        return try {
            loadingBottom.value = true
            val pos = params.key ?: 1
            if (pos>1) delay(2000)
            val response = apiService.searchPhotos(text =searchText , page = pos)
            loadingBottom.value = false
            LoadResult.Page(
                data = response.body()?.photos?.photo!!,
                prevKey = if (pos==1)null else pos-1,
                nextKey = if (pos == response.body()?.photos?.pages) null else pos+1
            )

        }catch (e: Exception) {
            loadingBottom.value = false
            LoadResult.Error(e)
        }

    }
}