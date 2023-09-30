package com.flicker.searchimage.di

import com.flicker.searchimage.util.Constants

import org.junit.Assert.assertEquals
import org.junit.Test

class AppModuleTest {

    @Test
    fun provideBaseURL_ReturnsCorrectValue() {
        val expectedBaseURL = Constants.BASE_URL

        val actualBaseURL = AppModule.provideBaseURL()

        assertEquals(expectedBaseURL, actualBaseURL)
    }
}
