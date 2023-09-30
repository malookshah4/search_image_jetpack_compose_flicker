package com.flicker.searchimage.di

import com.flicker.searchimage.network.ApiService
import com.flicker.searchimage.network.PhotoModel
import com.flicker.searchimage.network.PhotoX
import com.flicker.searchimage.network.Photos
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ApiServiceTest {

    @Mock
    private lateinit var mockApiService: ApiService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun searchPhotos_ReturnsResponse() = runBlocking {
        // Create a mock PhotoModel object
        val mockPhotoModel = PhotoModel(photos= Photos(page=1, pages=39840, perpage=25, photo= listOf(PhotoX(farm=66, id="53222104367", isfamily=0, isfriend=0, ispublic=1, owner="190477311@N05", secret="3f3ac7afaa", server="65535", title="Broadstone, Poole")), total = 25), stat = "")

        // Create a mock Response object
        val mockResponse = Response.success(mockPhotoModel)

        // Set up the mock ApiService to return the mock Response
        `when`(mockApiService.searchPhotos(text = "test")).thenReturn(mockResponse)

        // Call the searchPhotos function and get the actual Response
        val actualResponse = mockApiService.searchPhotos(text = "test")

        // Verify that the actual Response matches the mock Response
        assertEquals(mockResponse, actualResponse)
    }
}
