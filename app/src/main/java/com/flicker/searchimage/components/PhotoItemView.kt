package com.flicker.searchimage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.flicker.searchimage.network.PhotoX

@Composable
fun PhotoItemView(
    photo: PhotoX,
    onItemClick: (PhotoX) -> Unit) {

    val imageUrl =
        "https://farm${photo.farm}.static.flickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 10.dp)
                .border(width = 0.4.dp, color = Color.Gray)
                .clickable { onItemClick(photo) }
        ) {

            Image(
                modifier = Modifier
                    .size(height = 120.dp, width = 100.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(3.dp))
                    .clip(RoundedCornerShape(3.dp)),
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Image",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = photo.title,
                fontSize = 16.sp
            )
        }

//        HorizontalDivider(Modifier.padding(horizontal = 20.dp))
    }

}