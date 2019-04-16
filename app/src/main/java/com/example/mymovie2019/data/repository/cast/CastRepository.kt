package com.example.mymovie2019.data.repository.cast

import com.example.mymovie2019.data.local.datasource.cast.CastLocalDataSource
import com.example.mymovie2019.data.remote.datasource.cast.CastRemoteDataSource

interface CastRepository: CastRemoteDataSource, CastLocalDataSource {
}