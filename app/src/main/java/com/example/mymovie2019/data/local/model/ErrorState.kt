package com.example.mymovie2019.data.local.model;

sealed class ErrorState {

    data class NoAction(val messageId: Int) : ErrorState()
    data class WithAction(val messageId: Int, val action: () -> Unit, val actionNameId: Int) : ErrorState()
}