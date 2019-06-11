package com.example.mymovie2019.utils

class AppKey private constructor(){

    companion object {
        const val API_KEY = "e9e77b26e6d40c64ca295b46a58fe7a1"

        const val API_KEY_PARAMETER = "api_key"

        const val PAGE_PARAMETER = "page"

        const val EMPTY_COUNT = 0L

        private const val DEFAULT_SIZE_IMAGE = "w200"

        private const val SLIDER_SIZE_IMAGE = "w500"

        private const val CAST_ITEM_SIZE_IMAGE = "w200"

        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/"

        const val URL_MOVIE_ITEM_IMAGE_PATH = "$BASE_URL_IMAGE$DEFAULT_SIZE_IMAGE/"

        const val BASE_URL_SLIDER_IMAGE_PATH = "$BASE_URL_IMAGE$SLIDER_SIZE_IMAGE/"

        const val CAST_ITEM_URL_IMAGE_PATH = "https://image.tmdb.org/t/p/$CAST_ITEM_SIZE_IMAGE"

        const val KEY_INTENT_TO_MOVIE_DETAIL = "SliderMovieItem"
    }
}