package com.flicker.searchimage.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.flicker.searchimage.viewmodel.MyViewModel

@Composable
fun ImageViewScreen(viewModel: MyViewModel,navHostController: NavHostController) {

    val photo = viewModel.sharedPhoto.value
    Log.d("TAG", "ImageViewScreen: $photo")

    photo?.let {
        val imageUrl =
            "https://farm${photo.farm}.static.flickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Icon(
                modifier = Modifier.align(Alignment.TopStart)
                    .padding(10.dp)
                    .clickable { navHostController.navigateUp() },
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = "BACK ICON"
            )

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Image"
            )


        }

    }

}