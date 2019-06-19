package com.example.mymovie2019.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovie2019.data.local.model.GroupieMovieItem
import com.example.mymovie2019.data.local.model.MovieItem
import com.example.mymovie2019.data.local.model.MovieTransfer

abstract class BaseListMovieViewModel : BaseViewModel() {

    internal var page: Int = 0

    protected val _movieGroupieItems by lazy {
        MutableLiveData<List<GroupieMovieItem>>()
    }

    val movieGroupieItems: LiveData<List<GroupieMovieItem>>
        get() = _movieGroupieItems

    fun showMovies() {
        if (_movieGroupieItems.value == null) {
            loadMovies()
        }
    }

    fun onLoadMoreMovies(newPage: Int) {
        page = newPage
        loadMoreMovies(page) {
            _movieGroupieItems.value = it.map { movieItem ->
                GroupieMovieItem(movieItem)
            }
        }
    }

    fun getMovieTransfers(movieItem: MovieItem): MovieTransfer {
        return MovieTransfer(movieItem.id, movieItem.name, movieItem.imageUrl, movieItem.rating, movieItem.releaseDate)
    }

    protected abstract fun loadMovies()
    protected abstract fun loadMoreMovies(page: Int, onCompleted: (List<MovieItem>) -> Unit)
}