package com.example.mymovie2019.data.local.datasource.cast

import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.data.local.model.CastTransferContract
import javax.inject.Inject

class CastLocalDataSourceImp @Inject constructor() : CastLocalDataSource {

    override fun parseToCastTransfer(castTransferContract: CastTransferContract): CastTransfer {
        return CastTransfer(castTransferContract)
    }
}