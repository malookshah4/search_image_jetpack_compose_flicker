package com.flicker.searchimage.paging

import org.junit.jupiter.api.Assertions.*

import androidx.compose.runtime.MutableState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flicker.searchimage.network.ApiService
import com.flicker.searchimage.network.PhotoX
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class PhotoDataSourceTest {

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var loadingBottom: MutableState<Boolean>

    private lateinit var photoDataSource: PhotoDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        photoDataSource = PhotoDataSource(apiService, "searchText", loadingBottom)
    }



}
