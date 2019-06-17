package com.example.mymovie2019.data.local.datasource.cast

import com.example.mymovie2019.data.local.database.dao.CastDao
import com.example.mymovie2019.data.local.database.entity.CastEntity
import com.example.mymovie2019.data.local.model.CastItem
import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.data.local.model.CastTransferContract
import com.example.mymovie2019.data.remote.response.Cast
import com.example.mymovie2019.data.remote.response.CastRemote
import javax.inject.Inject

class CastLocalDataSourceImp @Inject constructor(private val castDao: CastDao) : CastLocalDataSource {

    override fun saveCastsFromCastRemotes(casts: List<CastRemote>) {
        if (casts.isNullOrEmpty()) {
            return
        }
        val castEntities = casts.map {
            CastEntity(it.id, 0, it.name, it.profilePath ?: "")
        }
        castDao.insertDatasWithReplace(castEntities)
    }

    override fun parseToCastTransfer(castTransferContract: CastTransferContract): CastTransfer {
        return CastTransfer(castTransferContract)
    }

    override fun saveCasts(casts: List<Cast>) {
        if (casts.isNullOrEmpty()) {
            return
        }
        val castEntities = casts.map {
            CastEntity(it.id, it.gender, it.name, it.imageUrl ?: "")
        }
        castDao.insertDatas(castEntities)
    }

    override fun getCastLocalOfMovie(movieId: Int): List<Cast> {
        val castLocals = castDao.getCastLocals(movieId)
        return castLocals.map {
            Cast(it.id, it.characterName,"",0,0, it.name, 0, it.profilePath)
        }
    }

    override fun isCastsSaved(page: Int): Boolean {
        val result = castDao.checkCastsSaved(page)
        return result > 0
    }

    override fun getCasts(page: Int): List<CastItem> {
        val castEntities = castDao.getCasts(page)
        return castEntities.map {
            CastItem(it.id, it.name, it.imagePath)
        }
    }
}