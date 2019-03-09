package com.example.mymovie2019.utils

class AppKey private constructor(){

    companion object {
        const val API_KEY = "e9e77b26e6d40c64ca295b46a58fe7a1"

        const val API_KEY_PARAMETER = "api_key"

        const val PAGE_PARAMETER = "page"

        const val EMPTY_COUNT = 0L

        const val DEFAULT_SIZE_IMAGE = "w200"

        const val BASE_URL_IMAGE_PATH = "https://image.tmdb.org/t/p/$DEFAULT_SIZE_IMAGE/"
    }
}