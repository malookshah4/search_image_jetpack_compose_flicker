package com.flicker.searchimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.flicker.searchimage.navigation.MyNavigation
import com.flicker.searchimage.ui.theme.SearchImageTheme
import com.flicker.searchimage.util.SearchHistoryManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchImageTheme {
                val navHostController = rememberNavController()
                val searchHistoryManager = SearchHistoryManager(this)
                MyNavigation(navController=navHostController, searchHistoryManager = searchHistoryManager)
            }
        }
    }
}


