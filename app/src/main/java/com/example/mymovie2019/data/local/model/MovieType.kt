package com.example.mymovie2019.data.local.model

enum class MovieType {
    POPULAR, UPCOMING, TOP_RATED;

    companion object {
        fun getTypeByValue(value: Int): MovieType {
            return when(value) {
                POPULAR.ordinal -> POPULAR
                UPCOMING.ordinal -> UPCOMING
                TOP_RATED.ordinal -> TOP_RATED
                else -> POPULAR
            }
        }
    }


}