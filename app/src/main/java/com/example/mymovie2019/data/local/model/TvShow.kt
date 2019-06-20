package com.example.mymovie2019.data.local.model

import com.example.mymovie2019.data.remote.response.Genre

data class TvShow(val id: Int,
                  val name: String,
                  val genres: List<Genre>,
                  val rating: Double)