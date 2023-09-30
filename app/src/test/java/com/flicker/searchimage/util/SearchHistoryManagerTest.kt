package com.flicker.searchimage.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchHistoryManagerTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockSharedPreferences: SharedPreferences

    @Mock
    private lateinit var mockEditor: SharedPreferences.Editor

    private lateinit var searchHistoryManager: SearchHistoryManager

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`(mockContext.getSharedPreferences("Search_Image", Context.MODE_PRIVATE))
            .thenReturn(mockSharedPreferences)
        `when`(mockSharedPreferences.edit()).thenReturn(mockEditor)
        searchHistoryManager = SearchHistoryManager(mockContext)
    }

    @Test
    fun testSetList() {
        val text = "example text"
        val oldList = arrayListOf("previous text")
        `when`(mockSharedPreferences.getString("searchList", null)).thenReturn(Gson().toJson(oldList))

        searchHistoryManager.setList(text)

        val capturedEditor = ArgumentCaptor.forClass(SharedPreferences.Editor::class.java)
        verify(mockSharedPreferences).edit()
        verify(mockEditor).putString(eq("searchList"), eq(Gson().toJson(oldList + text)))
        verify(mockEditor).apply()
    }

    @Test
    fun testGetList() {
        val expectedList = arrayListOf("example text")
        `when`(mockSharedPreferences.getString("searchList", null)).thenReturn(Gson().toJson(expectedList))

        val actualList = searchHistoryManager.getList()

        assertEquals(expectedList, actualList)
    }
}

