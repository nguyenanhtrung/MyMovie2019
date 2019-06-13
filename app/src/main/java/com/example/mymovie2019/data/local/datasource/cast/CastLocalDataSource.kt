package com.example.mymovie2019.data.local.datasource.cast

import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.data.local.model.CastTransferContract
import com.example.mymovie2019.data.remote.response.Cast

interface CastLocalDataSource {

    fun parseToCastTransfer(castTransferContract: CastTransferContract): CastTransfer

    fun saveCasts(casts: List<Cast>)

    fun getCastLocalOfMovie(movieId: Int): List<Cast>
}