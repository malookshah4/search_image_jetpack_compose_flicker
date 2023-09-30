package com.flicker.searchimage.di

import com.flicker.searchimage.util.Constants
import com.flicker.searchimage.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseURL() = Constants.BASE_URL

    @Singleton
    @Provides
    fun providesRetrofitInstance(baseURL: String): Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providesServiceApi(retrofit: Retrofit) = retrofit.create(ApiService::class.java)


}