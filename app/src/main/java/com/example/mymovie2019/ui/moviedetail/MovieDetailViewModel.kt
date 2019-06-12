package com.example.mymovie2019.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.data.local.model.Event
import com.example.mymovie2019.data.local.model.MovieDetail
import com.example.mymovie2019.data.remote.response.Cast
import com.example.mymovie2019.data.remote.response.Genre
import com.example.mymovie2019.data.repository.cast.CastRepository
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.data.repository.movie.MovieRepository
import com.example.mymovie2019.data.repository.moviedetail.MovieDetailRepository
import com.example.mymovie2019.domains.moviedetail.GetMovieDetailUseCase
import com.example.mymovie2019.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val movieDetailRepository: MovieDetailRepository,
                                               private val genreRepository: GenreRepository,
                                               private val castRepository: CastRepository) : BaseViewModel() {


    private val getMovieDetailUseCase by lazy {
        GetMovieDetailUseCase(this, movieDetailRepository, genreRepository, this)
    }

    private val _movieDetailLiveData by lazy {
        MutableLiveData<MovieDetail>()
    }
    internal val movieDetailLiveData: LiveData<MovieDetail>
        get() = _movieDetailLiveData

    internal var movieId = -1

    internal var currentPosCastSelected = -1

    private val _navigateCastDetail by lazy {
        MutableLiveData<Event<CastTransfer>>()
    }
    internal val navigateCastDetail: LiveData<Event<CastTransfer>>
        get() = _navigateCastDetail


    fun loadMovieDetail() {
        if (movieId == -1) {
            return
        }
        getMovieDetailUseCase.invoke(movieId) {
            _movieDetailLiveData.value = it
        }
    }

    fun getGenreNames(genres: List<Genre>?) = genreRepository.getGenreNames(genres)

    fun onClickCastItem(cast: Cast, position: Int) {
        currentPosCastSelected = position
        val castTransfer = castRepository.parseToCastTransfer(cast)
        _navigateCastDetail.value = Event(castTransfer)
    }
}