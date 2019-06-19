package com.example.mymovie2019.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovie2019.data.local.database.entity.GenreEntity
import com.example.mymovie2019.data.local.model.*
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.data.repository.movie.MovieRepository
import com.example.mymovie2019.domains.genres.GetGenresUseCase
import com.example.mymovie2019.domains.movies.GetMoviesUseCase
import com.example.mymovie2019.domains.movies.GetSliderPopularMoviesUseCase
import com.example.mymovie2019.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.NoSuchElementException
import kotlin.concurrent.schedule

class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository
) : BaseViewModel() {

    companion object {
        private const val NUMBER_OF_ITEM_SLIDER = 4
        private const val TIME_SLIDE_POPULAR_MOVIE: Long = 4000
        private const val DELAY_TIME_LOAD_MORE = 300L
    }

    private val getMoviesUseCase by lazy {
        GetMoviesUseCase(this, movieRepository, this)
    }

    private val getSliderMoviesUseCase by lazy {
        GetSliderPopularMoviesUseCase(this, movieRepository)
    }

    private val getGenresUseCase by lazy {
        GetGenresUseCase(this, genreRepository, this)
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

    private lateinit var timerSliderMovie: TimerTask

    private val _genresLiveData by lazy {
        MutableLiveData<MutableList<GenreEntity>>()
    }
    internal val genresLiveData: LiveData<MutableList<GenreEntity>>
        get() = _genresLiveData

    private val _navigateToSeeMoreMovie by lazy {
        MutableLiveData<Event<Triple<Int,MovieType,Array<MovieItem>>>>()
    }

    val navigateToSeeMoreMovie: LiveData<Event<Triple<Int,MovieType,Array<MovieItem>>>>
        get() = _navigateToSeeMoreMovie


    private var popularMoviePage = 1
    private var upComingMoviePage = 1
    private var topRatedMoviePage = 1


    fun showListMoviesType() {
        if (_moviesTypeLiveData.value.isNullOrEmpty()) {
            val moviesType = movieRepository.getMoviesTypeVerticalItems()
            _moviesTypeLiveData.value = moviesType
        }
    }


    fun loadAllMovies() {
        val movieVerticalItems = _moviesTypeLiveData.value
        movieVerticalItems?.let { movieVerItems ->
            if (movieVerItems[MovieType.POPULAR.ordinal].movieItems.size == 1 && movieVerItems[MovieType.UPCOMING.ordinal].movieItems.size == 1 &&
                movieVerItems[MovieType.TOP_RATED.ordinal].movieItems.size == 1
            ) {
                loadMovieByType(popularMoviePage, MovieType.POPULAR) {
                    startPopularMoviesSlider()
                    showMovies(it.toMutableList(), MovieType.POPULAR)
                }
                loadMovieByType(topRatedMoviePage, MovieType.TOP_RATED) {
                    showMovies(it.toMutableList(), MovieType.TOP_RATED)
                }
                loadMovieByType(upComingMoviePage, MovieType.UPCOMING) {
                    showMovies(it.toMutableList(), MovieType.UPCOMING)
                }
            }
        }

    }

    private fun loadMovieByType(page: Int, movieType: MovieType, onCompleted: (List<MovieItem>) -> Unit) {
        getMoviesUseCase.invoke(Pair(page, movieType), onCompleted)
    }

    private fun showMovies(movieItems: MutableList<MovieItem>, movieType: MovieType) {
        val movieTypesCopy = getMovieVerticalItemsCopy()
        movieTypesCopy[movieType.ordinal].movieItems.removeAt(0)
        movieTypesCopy[movieType.ordinal].movieItems.addAll(movieItems)
        _moviesTypeLiveData.value = movieTypesCopy
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
        if (movieItems[movieItems.size - 1].itemType == ItemType.LOAD_MORE) {
            return
        }
        movieItems.add(MovieItem(itemType = ItemType.LOAD_MORE))
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
            popularMoviePage++
            popularMoviePage
        }
        MovieType.UPCOMING -> {
            upComingMoviePage++
            upComingMoviePage
        }
        MovieType.TOP_RATED -> {
            topRatedMoviePage++
            topRatedMoviePage
        }
    }

    fun onLoadMoreMovies(page: Int, listPosition: Int) {
        //if not connected to internet && next page not exist in database then
        //Not allow load more, --current page
        val movieType = MovieType.getTypeByValue(listPosition)
        val newPageByType = updatePage(page, movieType)
        loadMoreMovies(newPageByType, movieType)
    }

    private fun loadMoreMovies(page: Int, movieType: MovieType) {
        launch {
            showLoadingMoreMovies(movieType)
            delay(DELAY_TIME_LOAD_MORE)
            getMoviesUseCase.invoke(Pair(page, movieType)) {
                hideLoadingMoreMovies(movieType)
                addAllMovies(it, movieType)
            }
        }
    }

    fun onClickTextSeeAllMovie(position: Int) {
        val tripleArgument = getArgumentsForSeeAllMovieFragment(position)
        _navigateToSeeMoreMovie.value = Event(tripleArgument)
    }

    private fun getArgumentsForSeeAllMovieFragment(position: Int): Triple<Int, MovieType,Array<MovieItem>> {
        val movieTypeItems = _moviesTypeLiveData.value ?: return Triple(popularMoviePage,MovieType.POPULAR, arrayOf())
        val movieItems = movieTypeItems[position].movieItems.toTypedArray()
        return when (position) {
            MovieType.POPULAR.ordinal -> Triple(popularMoviePage,MovieType.POPULAR ,movieItems)
            MovieType.TOP_RATED.ordinal -> Triple(topRatedMoviePage,MovieType.TOP_RATED, movieItems)
            MovieType.UPCOMING.ordinal -> Triple(upComingMoviePage,MovieType.UPCOMING, movieItems)
            else -> throw NoSuchElementException()
        }
    }

    private fun startPopularMoviesSlider() {
        showSliderMovieImage()
        slideMovieImagesByTimer()
    }

    private fun showSliderMovieImage() {
        getSliderMoviesUseCase.invoke(MovieType.POPULAR) {
            _sliderMoviesLiveData.value = it
        }
    }

    private fun slideMovieImagesByTimer() {

        timerSliderMovie = Timer().schedule(TIME_SLIDE_POPULAR_MOVIE, TIME_SLIDE_POPULAR_MOVIE) {
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

    fun loadGenreMovies() {
        if (_genresLiveData.value.isNullOrEmpty()) {
            getGenresUseCase.invoke(GenreCategory.MOVIE) {
                _genresLiveData.value = it.toMutableList()
            }
        }
    }

    fun onScrollViewEvent(sliderHeight: Int, scrollY: Int, oldScrollY: Int) {
        if (scrollY > sliderHeight) {
            timerSliderMovie.cancel()
        } else if (scrollY == 0 && oldScrollY != 0) {
            slideMovieImagesByTimer()
        }
    }

}