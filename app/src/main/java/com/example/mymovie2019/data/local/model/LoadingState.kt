package com.example.mymovie2019.data.local.model;

sealed class LoadingState {
    object Show : LoadingState()
    object Hide : LoadingState()
}