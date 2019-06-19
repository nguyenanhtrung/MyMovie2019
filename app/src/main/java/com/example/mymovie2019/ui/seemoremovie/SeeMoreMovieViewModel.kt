package com.example.mymovie2019.ui.seemoremovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovie2019.data.local.model.GroupieMovieItem
import com.example.mymovie2019.data.local.model.MovieItem
import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.data.local.model.MovieType
import com.example.mymovie2019.data.repository.movie.MovieRepository
import com.example.mymovie2019.domains.movies.GetMoviesUseCase
import com.example.mymovie2019.ui.base.BaseListMovieViewModel
import com.example.mymovie2019.ui.base.BaseViewModel
import javax.inject.Inject

class SeeMoreMovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : BaseListMovieViewModel() {

    lateinit var movieType: MovieType
    lateinit var movieItems: Array<MovieItem>

    private val getMoviesUseCase by lazy {
        GetMoviesUseCase(this, movieRepository, this)
    }

    override fun loadMovies() {
        val movies = movieItems.toMutableList()
        _movieGroupieItems.value = movies.map {
            GroupieMovieItem(it)
        }
    }

    override fun loadMoreMovies(page: Int, onCompleted: (List<MovieItem>) -> Unit) {
        getMoviesUseCase.invoke(Pair(page, movieType), onCompleted)
    }


}