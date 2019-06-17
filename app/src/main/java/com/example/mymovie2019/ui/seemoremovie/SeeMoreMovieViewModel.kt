package com.example.mymovie2019.ui.seemoremovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovie2019.data.local.model.GroupieMovieItem
import com.example.mymovie2019.data.local.model.MovieItem
import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.data.repository.movie.MovieRepository
import com.example.mymovie2019.ui.base.BaseViewModel
import javax.inject.Inject

class SeeMoreMovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : BaseViewModel() {
    var moviePage = 1
    lateinit var movieItems: Array<MovieItem>

    private val _movieGroupieItems by lazy {
        MutableLiveData<List<GroupieMovieItem>>()
    }

    val movieGroupieItems: LiveData<List<GroupieMovieItem>>
        get() = _movieGroupieItems

    fun loadMovieItems() {
        val movies = movieItems.toMutableList()
        _movieGroupieItems.value = movies.map {
            GroupieMovieItem(it)
        }
    }

    fun getMovieTransfers(movieItem: MovieItem): MovieTransfer {
        return MovieTransfer(movieItem.id, movieItem.name, movieItem.imageUrl, movieItem.rating, movieItem.releaseDate)
    }
}