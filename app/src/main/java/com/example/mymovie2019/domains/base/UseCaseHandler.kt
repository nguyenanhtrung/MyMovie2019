package com.example.mymovie2019.domains.base

interface UseCaseHandler<T> {

    fun onSuccess(result: T)
}