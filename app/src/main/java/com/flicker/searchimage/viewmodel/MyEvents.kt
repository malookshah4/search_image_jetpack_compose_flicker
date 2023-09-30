package com.flicker.searchimage.viewmodel

import com.flicker.searchimage.network.PhotoX

sealed class MyEvents {
    data class OnSearchClick(val searchText : String) : MyEvents()
    data class OnImageClick(val photoX: PhotoX) : MyEvents()
    data class OnNextPage(val searchText: String, val page: Int) : MyEvents()
}
