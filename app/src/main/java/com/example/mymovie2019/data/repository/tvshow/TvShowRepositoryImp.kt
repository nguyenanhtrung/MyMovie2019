package com.example.mymovie2019.data.repository.tvshow

import com.example.mymovie2019.data.local.datasource.tvshow.TvShowLocalDataSource
import com.example.mymovie2019.data.local.model.TvShowGroupieItem
import com.example.mymovie2019.data.local.model.TvShowType
import com.example.mymovie2019.data.remote.datasource.tvshow.TvShowRemoteDataSource
import com.example.mymovie2019.data.remote.response.TvShowRemote
import com.example.mymovie2019.data.remote.response.TvShowsResponse
import javax.inject.Inject

class TvShowRepositoryImp @Inject constructor(
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowRemoteDataSource: TvShowRemoteDataSource
) : TvShowRepository {

    override fun saveTvShows(page: Int, tvShowType: TvShowType, tvShowRemotes: List<TvShowRemote>) =
        tvShowLocalDataSource.saveTvShows(page, tvShowType, tvShowRemotes)

    override fun getTvShows(page: Int, tvShowType: TvShowType): List<TvShowGroupieItem> =
        tvShowLocalDataSource.getTvShows(page, tvShowType)

    override fun checkTvShowsExist(page: Int, tvShowType: TvShowType): Boolean =
        tvShowLocalDataSource.checkTvShowsExist(page, tvShowType)

    override suspend fun getTvShowsAsync(page: Int, tvShowType: TvShowType): TvShowsResponse {
        return tvShowRemoteDataSource.getTvShowsAsync(page, tvShowType)
    }

}