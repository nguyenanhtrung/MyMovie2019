package com.example.mymovie2019.domains.base

import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.ErrorState
import com.example.mymovie2019.data.remote.response.BaseResponse
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.utils.NetworkBoundResource
import kotlinx.coroutines.*

abstract class BaseRemoteUseCase<in Param, Response, MappedData>(
    private val uiScope: CoroutineScope,
    private val callBack: InteractionWithUICallback
) where Response : BaseResponse {

    companion object {
        const val TIME_OUT = 20000L
        const val SUCCESS_RESPONSE_CODE = 0
    }


    private val networkBoundResource by lazy {
        createNetworkBoundResource()
    }

    operator fun invoke(parameter: Param, onCompleted: (MappedData) -> Unit) {
        uiScope.launch {
            val connection = withTimeoutOrNull(TIME_OUT) {
                when (withContext(Dispatchers.IO) {
                    networkBoundResource.isSavedToLocal(parameter)
                }) {
                    true -> {
                        if (networkBoundResource.shouldLoadFromLocal()) {
                            val result = withContext(Dispatchers.IO) {
                                networkBoundResource.loadFromLocal(parameter)
                            }
                            onCompleted(result)
                        }

                    }
                    else -> loadFromRemote(parameter, onCompleted)
                }
            }
            connection ?: callBack.showError(ErrorState.NoAction(R.string.error_time_out))
        }
    }

    private suspend fun loadFromRemote(parameter: Param, onCompleted: (MappedData) -> Unit) {
        if (networkBoundResource.isShowLoading()) {
            callBack.showLoading()
        }
        val result = execute(parameter)
        callBack.hideLoading()
        when (result.statusCode) {
            SUCCESS_RESPONSE_CODE -> {
                withContext(Dispatchers.IO) {
                    networkBoundResource.saveToLocal(result)
                }
                val resultMapped: MappedData = networkBoundResource.mapFrom(result)
                onCompleted(resultMapped)
            }
            else -> {
                callBack.showError(ErrorState.NoAction(R.string.error_network_call))
            }
        }
    }

    abstract suspend fun execute(parameter: Param): Response
    protected abstract fun createNetworkBoundResource(): NetworkBoundResource<in Param,Response, MappedData>


}