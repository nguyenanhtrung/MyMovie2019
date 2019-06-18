package com.example.mymovie2019.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovie2019.data.local.model.GroupieMovieItem

class BaseListMovieViewModel : BaseViewModel() {

    private val _movieGroupieItems by lazy {
        MutableLiveData<List<GroupieMovieItem>>()
    }

    val movieGroupieItems: LiveData<List<GroupieMovieItem>>
        get() = _movieGroupieItems
}