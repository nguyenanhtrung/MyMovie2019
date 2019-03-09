package com.example.mymovie2019.domains.base

import androidx.lifecycle.MutableLiveData
import com.example.mymovie2019.data.local.model.ApiResult
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

    fun callApi(parameter: P, result: MutableLiveData<ApiResult>) {
        launch {
            result.value = ApiResult.Loading
            val response = execute(parameter)
            result.value = ApiResult.Success(response)
        }
    }



    internal suspend fun executeSync(parameter: P): Result<R> = try {
        Result.success(execute(parameter))
    } catch (exception: Exception) {
        Result.failure(exception)
    }


    protected abstract suspend fun execute(parameter: P): R

}