package com.example.mymovie2019.data.remote.datasource.tvshow

import com.example.mymovie2019.data.local.model.TvShowType
import com.example.mymovie2019.data.remote.response.TvShowsResponse
import com.example.mymovie2019.data.remote.service.ApiService
import com.example.mymovie2019.utils.AppKey
import javax.inject.Inject

class TvShowRemoteDataSourceImp @Inject constructor(private val apiService: ApiService) : TvShowRemoteDataSource {

    override suspend fun getTvShowsAsync(page: Int, tvShowType: TvShowType): TvShowsResponse = when(tvShowType) {
        TvShowType.POPULAR -> apiService.getPopularTvShowsAsync(page, AppKey.API_KEY).await()
        TvShowType.LATEST -> apiService.getLatestTvShowsAsync(page, AppKey.API_KEY).await()
        TvShowType.ON_THE_AIR -> apiService.getOnTheAirTvShowsAsync(page, AppKey.API_KEY).await()
    }
}