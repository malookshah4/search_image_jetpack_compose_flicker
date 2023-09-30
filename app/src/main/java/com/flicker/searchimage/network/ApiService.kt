package com.flicker.searchimage.network

import com.flicker.searchimage.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/services/rest/")
    suspend fun searchPhotos(
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("text") text: String,
        @Query("per_page") perPage: Int = 25,
        @Query("page") page: Int = 1 // Add this line for pagination
    ): Response<PhotoModel>
}
