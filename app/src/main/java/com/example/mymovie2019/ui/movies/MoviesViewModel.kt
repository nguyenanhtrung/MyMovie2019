package com.example.mymovie2019.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovie2019.data.local.model.ItemType
import com.example.mymovie2019.data.local.model.MovieItem
import com.example.mymovie2019.data.local.model.MovieType
import com.example.mymovie2019.data.local.model.MoviesVerticalItem
import com.example.mymovie2019.data.remote.response.MovieRemote
import com.example.mymovie2019.data.remote.response.MoviesResponse
import com.example.mymovie2019.data.repository.movie.MovieRepository
import com.example.mymovie2019.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) : BaseViewModel() {

    companion object {
        private const val NUMBER_OF_ITEM_SLIDER = 4
        private const val TIME_SLIDE_POPULAR_MOVIE: Long = 3000
    }

    private val _moviesTypeLiveData by lazy {
        MutableLiveData<MutableList<MoviesVerticalItem>>()
    }
    internal val moviesTypeLiveData: LiveData<MutableList<MoviesVerticalItem>>
        get() = _moviesTypeLiveData

    private val _sliderMoviesLiveData by lazy {
        MutableLiveData<List<MovieItem>>()
    }
    internal val sliderMoviesLiveData: LiveData<List<MovieItem>>
        get() = _sliderMoviesLiveData

    private val _pageSliderMovieLiveData by lazy {
        MutableLiveData<Int>()
    }
    internal val pageSliderMovieLiveData: LiveData<Int>
        get() = _pageSliderMovieLiveData

    private var popularMoviePage = 1
    private var upComingMoviePage = 1
    private var topRatedMoviePage = 1


    fun showListMoviesType() {
        launch {
            val moviesType = movieRepository.getMoviesTypeVerticalItems()
            _moviesTypeLiveData.value = moviesType
        }

    }

    fun loadAllMovies() {
        launch {
            val popularMovieResponse = movieRepository.getMoviesAsync(popularMoviePage, MovieType.POPULAR)
            val upComingMovieResponse = movieRepository.getMoviesAsync(upComingMoviePage, MovieType.UPCOMING)
            val topRatedMovieResponse = movieRepository.getMoviesAsync(topRatedMoviePage, MovieType.TOP_RATED)

            loadMovies(popularMovieResponse.await(), MovieType.POPULAR.ordinal)
            loadMovies(upComingMovieResponse.await(), MovieType.UPCOMING.ordinal)
            loadMovies(topRatedMovieResponse.await(), MovieType.TOP_RATED.ordinal)

            startPopularMoviesSlider()
        }
    }

    private fun loadMovies(moviesResponse: MoviesResponse?, movieTypeIndex: Int) {
        moviesResponse?.let {
            val movieRemotes = moviesResponse.movieRemotes
            val movieItems = mapToMovieItems(movieRemotes)
            val movieTypesCopy = getMovieVerticalItemsCopy()
            movieTypesCopy[movieTypeIndex].movieItems.removeAt(0)
            movieTypesCopy[movieTypeIndex].movieItems.addAll(movieItems)
            _moviesTypeLiveData.value = movieTypesCopy
        }
    }

    private fun getMovieVerticalItemsCopy(): MutableList<MoviesVerticalItem> {
        val movieTypesOriginal = _moviesTypeLiveData.value ?: return mutableListOf()
        val movieTypesCopy = mutableListOf<MoviesVerticalItem>()
        for (index in 0 until movieTypesOriginal.size) {
            val movieTypeItem = MoviesVerticalItem(movieTypesOriginal[index])
            movieTypesCopy.add(movieTypeItem)
        }
        return movieTypesCopy
    }

    private fun showLoadingMoreMovies(movieType: MovieType) {
        val moviesTypesCopy = getMovieVerticalItemsCopy()
        val moviesTypeItemCopy = moviesTypesCopy[movieType.ordinal]
        val movieItems = moviesTypeItemCopy.movieItems
        movieItems.add(MovieItem(itemType = ItemType.ItemLoading))
        _moviesTypeLiveData.value = moviesTypesCopy
    }

    private fun hideLoadingMoreMovies(movieType: MovieType) {
        val moviesTypesCopy = getMovieVerticalItemsCopy()
        val moviesTypeItemCopy = moviesTypesCopy[movieType.ordinal]
        val movieItems = moviesTypeItemCopy.movieItems
        movieItems.removeAt(movieItems.size - 1)
        _moviesTypeLiveData.value = moviesTypesCopy
    }

    private fun addAllMovies(movies: List<MovieItem>, movieType: MovieType) {
        val moviesTypesCopy = getMovieVerticalItemsCopy()
        val moviesTypeItemCopy = moviesTypesCopy[movieType.ordinal]
        val movieItems = moviesTypeItemCopy.movieItems
        movieItems.addAll(movies)
        _moviesTypeLiveData.value = moviesTypesCopy
    }

    private fun updatePage(page: Int, movieType: MovieType) = when (movieType) {
        MovieType.POPULAR -> {
            popularMoviePage = page + 1
            popularMoviePage
        }
        MovieType.UPCOMING -> {
            upComingMoviePage = page + 1
            upComingMoviePage
        }
        MovieType.TOP_RATED -> {
            topRatedMoviePage = page + 1
            topRatedMoviePage
        }
    }

    fun onLoadMoreMovies(page: Int, listPosition: Int) {
        val movieType = MovieType.getTypeByValue(listPosition)
        updatePage(page, movieType)
        loadMoreMovies(popularMoviePage, movieType)
    }

    private fun loadMoreMovies(page: Int, movieType: MovieType) {
        launch {
            showLoadingMoreMovies(movieType)
            val moviesResponse = movieRepository.getMoviesAsync(page, movieType).await()
            val movieRemotes = moviesResponse.movieRemotes
            val movieItems = mapToMovieItems(movieRemotes)
            hideLoadingMoreMovies(movieType)
            addAllMovies(movieItems, movieType)
        }
    }

    private fun mapToMovieItems(movieRemotes: List<MovieRemote>) = movieRemotes.map {
        MovieItem(it.id, it.title, it.releaseDate, it.voteAverage, it.posterPath)
    }

    private fun getMovieItemsByType(movieType: MovieType): List<MovieItem> {
        val movieTypeItems = _moviesTypeLiveData.value ?: return emptyList()
        val movieTypeItem = movieTypeItems[movieType.ordinal]
        return movieTypeItem.movieItems
    }

    private fun getPopularMoviesForSlider(): List<MovieItem> {
        val popularMovies = getMovieItemsByType(MovieType.POPULAR)
        return popularMovies.sortedByDescending { it.rating }
            .take(NUMBER_OF_ITEM_SLIDER)
    }

    fun startPopularMoviesSlider() {
        showSliderMovieImage()
        slideMovieImagesByTimer()
    }

    private fun showSliderMovieImage() {
        val movieItems = getPopularMoviesForSlider()
        _sliderMoviesLiveData.value = movieItems
    }

    private fun slideMovieImagesByTimer() {

        Timer().schedule(TIME_SLIDE_POPULAR_MOVIE, TIME_SLIDE_POPULAR_MOVIE) {
            if (_pageSliderMovieLiveData.value == null) {
                _pageSliderMovieLiveData.postValue(0)
            } else {
                val currentPage = _pageSliderMovieLiveData.value!!
                if (currentPage == NUMBER_OF_ITEM_SLIDER) {
                    _pageSliderMovieLiveData.postValue(0)
                } else {
                    _pageSliderMovieLiveData.postValue(currentPage + 1)
                }
            }
        }
    }
}