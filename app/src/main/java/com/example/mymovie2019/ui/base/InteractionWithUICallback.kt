package com.example.mymovie2019.ui.base

import com.example.mymovie2019.data.local.model.ErrorState

interface InteractionWithUICallback {

    fun showLoading();
    fun hideLoading();
    fun showError(errorState: ErrorState)
}