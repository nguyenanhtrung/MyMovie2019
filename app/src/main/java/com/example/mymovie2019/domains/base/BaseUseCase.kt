package com.example.mymovie2019.domains.base

import com.example.mymovie2019.ui.base.UseCaseHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<in P, R> : CoroutineScope {


    private val job = Job()

    private val handlerException = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + handlerException



    internal abstract fun invoke(parameter: P, useCaseHandler: UseCaseHandler<R>)



    internal suspend fun executeSync(parameter: P): Result<R> = try {
        Result.success(execute(parameter))
    } catch (exception: Exception) {
        Result.failure(exception)
    }


    protected abstract suspend fun execute(parameter: P): R

    internal fun cancel() {
        job.cancel()
    }

}