package com.example.mymovie2019.ui.base;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovie2019.data.local.model.ErrorState
import com.example.mymovie2019.data.local.model.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val _loadingLiveData by lazy {
        MutableLiveData<LoadingState>()
    }

    val loadingLiveData: LiveData<LoadingState>
        get() = _loadingLiveData

    private val _errorStateLiveData by lazy {
        MutableLiveData<ErrorState>()
    }

    val errorStateLiveData: LiveData<ErrorState>
        get() = _errorStateLiveData

    internal fun showLoading() {
        _loadingLiveData.value = LoadingState.Show
    }

    internal fun hideLoading() {
        _loadingLiveData.value = LoadingState.Hide
    }

    internal fun showError(errorState: ErrorState) {
        _errorStateLiveData.value = errorState
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}