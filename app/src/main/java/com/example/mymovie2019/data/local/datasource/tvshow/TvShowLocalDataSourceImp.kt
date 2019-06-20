package com.example.mymovie2019.data.local.datasource.tvshow

import com.example.mymovie2019.data.local.database.dao.TvShowDao
import com.example.mymovie2019.data.local.database.entity.TvShowEntity
import com.example.mymovie2019.data.local.model.TvShow
import com.example.mymovie2019.data.local.model.TvShowGroupieItem
import com.example.mymovie2019.data.local.model.TvShowType
import com.example.mymovie2019.data.remote.response.TvShowRemote
import javax.inject.Inject

class TvShowLocalDataSourceImp @Inject constructor(private val tvShowDao: TvShowDao) : TvShowLocalDataSource {

    override fun saveTvShows(page: Int, tvShowType: TvShowType, tvShowRemotes: List<TvShowRemote>) {
        val tvShowEntities = tvShowRemotes.map {
            TvShowEntity(
                it.id ?: 0,
                it.name ?: "",
                it.posterPath ?: "",
                it.voteAverage ?: 0.0,
                it.firstAirDate ?: "",
                tvShowType.name,
                page,
            )
        }
        tvShowDao.insertDatasWithReplace(tvShowEntities)
    }

    override fun getTvShows(page: Int, tvShowType: TvShowType): List<TvShowGroupieItem> {
        return tvShowDao.getTvShows(page, tvShowType.name).map {
            TvShowGroupieItem(TvShow(
                it.id,
                it.name
            ))
        }
    }

    override fun checkTvShowsExist(page: Int, tvShowType: TvShowType): Boolean {
        return tvShowDao.isExistsTvShow(page, tvShowType.name) > 0
    }
}