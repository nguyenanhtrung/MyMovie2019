package com.example.mymovie2019.data.local.datasource.tvshow

import com.example.mymovie2019.data.local.model.TvShowGroupieItem
import com.example.mymovie2019.data.local.model.TvShowType
import com.example.mymovie2019.data.remote.response.TvShowRemote

interface TvShowLocalDataSource {
    fun saveTvShows(page: Int, tvShowType: TvShowType, tvShowRemotes: List<TvShowRemote>)

    fun getTvShows(page: Int, tvShowType: TvShowType) : List<TvShowGroupieItem>

    fun checkTvShowsExist(page: Int, tvShowType: TvShowType) : Boolean
}