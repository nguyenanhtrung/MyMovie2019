package com.example.mymovie2019.ui.base

interface UseCaseHandler<T> {

    fun onSuccess(result: T)
}