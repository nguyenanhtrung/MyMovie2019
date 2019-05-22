package com.example.mymovie2019.utils

import com.example.mymovie2019.data.remote.response.BaseResponse

abstract class NetworkBoundResource<Param,Response, MappedData> where Response : BaseResponse{

    open fun shouldLoadFromLocal(): Boolean = true

    open fun isShowLoading(): Boolean = true

    abstract fun loadFromLocal(param: Param): MappedData

    abstract fun saveToLocal(response: Response)

    abstract fun isSavedToLocal(param: Param): Boolean

    abstract fun  mapFrom(response: Response): MappedData
}