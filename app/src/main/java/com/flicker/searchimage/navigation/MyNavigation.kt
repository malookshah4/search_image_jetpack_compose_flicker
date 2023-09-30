package com.flicker.searchimage.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.flicker.searchimage.screens.ImageViewScreen
import com.flicker.searchimage.screens.SearchScreen
import com.flicker.searchimage.util.SearchHistoryManager
import com.flicker.searchimage.viewmodel.MyViewModel

@Composable
fun MyNavigation(
    viewModel: MyViewModel = hiltViewModel(),
    navController: NavHostController,
    searchHistoryManager: SearchHistoryManager
) {

    NavHost(navController = navController, startDestination = Screens.SEARCH_SCREEN.name ) {
        composable(route=Screens.SEARCH_SCREEN.name) {
            SearchScreen(navHostController=navController, viewModel = viewModel, searchHistoryManager =searchHistoryManager )
        }

        composable(route= Screens.IMAGE_VIEW_SCREEN.name) {
            ImageViewScreen(viewModel,navController)
        }
    }
    
}