package com.example.mymovie2019.data.repository.tvshow

import com.example.mymovie2019.data.local.datasource.tvshow.TvShowLocalDataSource
import com.example.mymovie2019.data.remote.datasource.tvshow.TvShowRemoteDataSource

interface TvShowRepository : TvShowLocalDataSource, TvShowRemoteDataSource {
}