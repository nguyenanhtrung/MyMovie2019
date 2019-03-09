package com.example.mymovie2019.data.local.model

sealed class ApiResult {

    object Loading: ApiResult()
    class Success<out R>(val data: R): ApiResult()
    class Failure(val exception: Throwable): ApiResult()
}