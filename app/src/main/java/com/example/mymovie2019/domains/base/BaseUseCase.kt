package com.example.mymovie2019.domains.base

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<in P, R> : CoroutineScope {

    private val job = Job()

    private val handlerException = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + handlerException


    operator fun invoke(parameter: P, result: MutableLiveData<Result<R>>) {
        launch {
            withContext(Dispatchers.IO) {
                val response = execute(parameter)
                result.postValue(Result.success(response))
            }
        }
    }

    internal fun executeSync(parameter: P): Result<R> = try {
        Result.success(execute(parameter))
    } catch (exception: Exception) {
        Result.failure(exception)
    }


    protected abstract fun execute(parameter: P): R

}