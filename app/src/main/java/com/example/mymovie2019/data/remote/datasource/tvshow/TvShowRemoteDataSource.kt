package com.example.mymovie2019.data.remote.datasource.tvshow

import com.example.mymovie2019.data.local.model.TvShowType
import com.example.mymovie2019.data.remote.response.TvShowsResponse

interface TvShowRemoteDataSource {

    suspend fun getTvShowsAsync(page: Int, tvShowType: TvShowType): TvShowsResponse
}