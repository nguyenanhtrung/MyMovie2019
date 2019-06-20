package com.example.mymovie2019.ui.castdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.data.remote.response.CastMovieResponse
import com.example.mymovie2019.data.remote.response.CastTvShowResponse
import com.example.mymovie2019.data.repository.cast.CastRepository
import com.example.mymovie2019.data.repository.movie.MovieRepository
import com.example.mymovie2019.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CastDetailViewModel @Inject constructor(private val castRepository: CastRepository,
                                              private val movieRepository: MovieRepository) : BaseViewModel() {

    var castId: Int = -1


    private val _castCredits by lazy {
        MutableLiveData<Pair<MutableList<MovieTransfer>, MutableList<MovieTransfer>>>()
    }

    internal val castCredits: LiveData<Pair<MutableList<MovieTransfer>, MutableList<MovieTransfer>>>
        get() = _castCredits


    fun loadCastCredits() {
        launch {
            delay(500)
            if (castId != -1 ) {
                showLoading()
                val castMoviesResult = castRepository.getMoviesOfCastAsync(castId)
                val castTvShowsResult = castRepository.getTvShowsOfCastAsync(castId)
                showCastCredits(castMoviesResult.await(),castTvShowsResult.await())
                hideLoading()
            }
        }
    }

    private fun showCastCredits(castMovieResponse: CastMovieResponse, castTvShowResponse: CastTvShowResponse) {
//        val castMovieTransfers = movieRepository.getMovieTransfers(castMovieResponse.castMovies)
//        val castTvShowTransfers = movieRepository.getMovieTransfers(castTvShowResponse.castTvShows)
//
//        val castCreditPair = Pair(castMovieTransfers, castTvShowTransfers)
//        _castCredits.value = castCreditPair
    }
}