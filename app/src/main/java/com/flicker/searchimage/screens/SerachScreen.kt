package com.flicker.searchimage.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.paging.map
import com.flicker.searchimage.components.PhotoItemView
import com.flicker.searchimage.navigation.Screens
import com.flicker.searchimage.util.SearchHistoryManager
import com.flicker.searchimage.viewmodel.MyEvents
import com.flicker.searchimage.viewmodel.MyViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: MyViewModel,
    navHostController: NavHostController,
    searchHistoryManager: SearchHistoryManager,
) {

    val photos = viewModel.photoList.collectAsLazyPagingItems()
    var loading by remember() { mutableStateOf(false) }


    var searchActive by remember { mutableStateOf(false) }
    val scrollState = rememberLazyListState()

    val lastVisibleItemIndex: Int by remember {
        derivedStateOf {
            val layoutInfo = scrollState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            visibleItemsInfo.lastOrNull()?.index ?: -1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        SearchBar(
            query = viewModel.searchText.value,
            onQueryChange = { viewModel.searchText.value = it },
            onSearch = {
                searchHistoryManager.setList(it)
                viewModel.onEvent(MyEvents.OnSearchClick(it))
                searchActive = false
                loading = true
            },
            active = searchActive,
            onActiveChange = { searchActive = it },
            placeholder = { Text(text = "Search image here") },
            colors = SearchBarDefaults.colors(
                dividerColor = Color.Transparent,
                containerColor = Color.Gray.copy(alpha = 0.4f)
            ),
            shape = RoundedCornerShape(4.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )
            },
            trailingIcon = {
                if (viewModel.searchText.value.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable { viewModel.searchText.value = "" },
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "Clear",
                        tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )

                }
            }
        ) {

            val searchHistoryList = searchHistoryManager.getList()
            Text(
                text = "Search History",
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            HorizontalDivider()
            searchHistoryList.let { list ->
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(list) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 10.dp)
                                .clickable {
                                    viewModel.onEvent(MyEvents.OnSearchClick(it))
                                    searchActive = false
                                    loading = true
                                    viewModel.searchText.value = it
                                },
                            text = it,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }

        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    state = scrollState,
                ) {

                    items(
                        count = photos.itemCount,
                        contentType = photos.itemContentType { "Photox" }
                    ) {
                        loading = false
                        val item = photos[it]
                        item?.let {
                            PhotoItemView(photo = item) { photo ->
                                viewModel.sharedPhoto.value = photo
                                navHostController.navigate(Screens.IMAGE_VIEW_SCREEN.name)
                            }

                        }
                    }
                    item {
                        if (viewModel.loadingBottom.value) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(50.dp),
                                    strokeWidth = 2.dp
                                )
                            }
                        }
                    }

                }
            }
        }
    }

}