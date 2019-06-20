package com.example.mymovie2019.data.repository.tvshow

import com.example.mymovie2019.data.local.datasource.tvshow.TvShowLocalDataSource
import com.example.mymovie2019.data.local.model.TvShowType
import com.example.mymovie2019.data.remote.datasource.tvshow.TvShowRemoteDataSource
import com.example.mymovie2019.data.remote.response.TvShowsResponse
import javax.inject.Inject

class TvShowRepositoryImp @Inject constructor(
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowRemoteDataSource: TvShowRemoteDataSource
) : TvShowRepository {

    override suspend fun getTvShowsAsync(page: Int, tvShowType: TvShowType): TvShowsResponse {
        return tvShowRemoteDataSource.getTvShowsAsync(page, tvShowType)
    }
}