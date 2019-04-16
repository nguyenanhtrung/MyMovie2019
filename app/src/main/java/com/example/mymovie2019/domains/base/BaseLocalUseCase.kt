package com.example.mymovie2019.domains.base

import com.example.mymovie2019.ui.base.UseCaseHandler
import kotlinx.coroutines.launch

abstract class BaseLocalUseCase<P, R> : BaseUseCase<P,R>() {

    override fun invoke(parameter: P, useCaseHandler: UseCaseHandler<R>) {
        launch {
            val result = execute(parameter)
            useCaseHandler.onSuccess(result)
        }
    }

}