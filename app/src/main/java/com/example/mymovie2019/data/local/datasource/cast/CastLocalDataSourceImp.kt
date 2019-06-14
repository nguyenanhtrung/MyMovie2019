package com.example.mymovie2019.data.local.datasource.cast

import com.example.mymovie2019.data.local.database.dao.CastDao
import com.example.mymovie2019.data.local.database.entity.CastEntity
import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.data.local.model.CastTransferContract
import com.example.mymovie2019.data.remote.response.Cast
import javax.inject.Inject

class CastLocalDataSourceImp @Inject constructor(private val castDao: CastDao) : CastLocalDataSource {

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
}