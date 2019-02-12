package com.example.mymovie2019.data.remote.response

import com.squareup.moshi.Json

abstract class BaseResponse {

    @Json(name = "status_code")
    val statusCode = 0

    @Json(name = "status_message")
    val statusMessage = ""

}