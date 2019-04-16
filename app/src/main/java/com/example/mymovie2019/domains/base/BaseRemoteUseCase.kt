package com.example.mymovie2019.domains.base

import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.ErrorState
import com.example.mymovie2019.data.remote.response.BaseResponse
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.ui.base.UseCaseHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

abstract class BaseRemoteUseCase<P, RP>(private val callBack: InteractionWithUICallback) :
    BaseUseCase<P, RP>() where RP : BaseResponse {

    companion object {
        const val TIME_OUT = 20000L
        const val SUCCESS_RESPONSE_CODE = 0
    }

    override fun invoke(parameter: P, useCaseHandler: UseCaseHandler<RP>) {
        launch {
            val connection = withTimeoutOrNull(TIME_OUT) {
                callBack.showLoading()
                val result = execute(parameter)
                callBack.hideLoading()
                when (result.statusCode) {
                    SUCCESS_RESPONSE_CODE -> useCaseHandler.onSuccess(result)
                    else -> {
                        callBack.showError(ErrorState.NoAction(R.string.error_network_call))
                    }
                }
            }
            if (connection == null) {
                callBack.showError(ErrorState.NoAction(R.string.error_time_out))
            }
        }
    }
}