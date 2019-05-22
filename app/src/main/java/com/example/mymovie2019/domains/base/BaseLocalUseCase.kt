package com.example.mymovie2019.domains.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseLocalUseCase<in P, R>(private val uiScope: CoroutineScope) {


    operator fun invoke(parameter: P, onCompleted: (R) -> Unit) {
         uiScope.launch {
             val result = execute(parameter)
             onCompleted(result)
         }
     }

    protected abstract suspend fun execute(parameter: P): R

}