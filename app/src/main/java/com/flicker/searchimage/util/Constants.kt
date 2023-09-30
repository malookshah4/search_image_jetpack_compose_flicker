package com.flicker.searchimage.util

class Constants {
    companion object {
         const val API_KEY = "1508443e49213ff84d566777dc211f2a"
         const val BASE_URL = "https://api.flickr.com"

        fun getUrl(searchText: String): String {
            return "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_" +
                    "key=$API_KEY&format=json&nojsoncallback=" +
                    "1&text=$searchText&per_page=25"
        }
    }
}