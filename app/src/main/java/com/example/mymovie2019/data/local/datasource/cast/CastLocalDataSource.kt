package com.example.mymovie2019.data.local.datasource.cast

import com.example.mymovie2019.data.local.model.CastItem
import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.data.local.model.CastTransferContract
import com.example.mymovie2019.data.remote.response.Cast
import com.example.mymovie2019.data.remote.response.CastRemote

interface CastLocalDataSource {

    fun parseToCastTransfer(castTransferContract: CastTransferContract): CastTransfer

    fun saveCasts(casts: List<Cast>)

    fun saveCastsFromCastRemotes(casts: List<CastRemote>)

    fun getCastLocalOfMovie(movieId: Int): List<Cast>

    fun isCastsSaved(page: Int): Boolean

    fun getCasts(page: Int): List<CastItem>
}