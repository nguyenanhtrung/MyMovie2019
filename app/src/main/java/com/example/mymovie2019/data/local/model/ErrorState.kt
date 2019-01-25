package com.example.mymovie2019.data.local.model;

sealed class ErrorState {

    data class NoAction(val message: String) : ErrorState()
    data class WithAction(val message: String, val action: () -> Unit, val actionName: String) : ErrorState()
}