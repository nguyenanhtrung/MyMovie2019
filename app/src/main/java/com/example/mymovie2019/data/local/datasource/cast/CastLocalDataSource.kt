package com.example.mymovie2019.data.local.datasource.cast

import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.data.local.model.CastTransferContract

interface CastLocalDataSource {

    fun parseToCastTransfer(castTransferContract: CastTransferContract): CastTransfer
}