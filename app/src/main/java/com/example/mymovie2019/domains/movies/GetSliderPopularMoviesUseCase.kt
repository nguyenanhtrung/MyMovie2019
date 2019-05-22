package com.example.mymovie2019.domains.movies

import com.example.mymovie2019.data.local.model.MovieItem
import com.example.mymovie2019.data.local.model.MovieType
import com.example.mymovie2019.data.repository.movie.MovieRepository
import com.example.mymovie2019.domains.base.BaseLocalUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSliderPopularMoviesUseCase(
    uiScope: CoroutineScope,
    private val movieRepository: MovieRepository
) : BaseLocalUseCase<MovieType, List<MovieItem>>(uiScope) {

    companion object {
        private const val DEFAULT_SLIDER_MOVIES = 4
    }

    override suspend fun execute(parameter: MovieType): List<MovieItem> {
        val movieEntities = withContext(Dispatchers.IO) {
            movieRepository.getMoviesSortByRating(DEFAULT_SLIDER_MOVIES,parameter)
        }
        return movieEntities.map {
            MovieItem(it.id, it.name, it.releaseDate, it.rating, it.imageUrl)
        }
    }
}