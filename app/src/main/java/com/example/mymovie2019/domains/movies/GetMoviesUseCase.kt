package com.example.mymovie2019.domains.movies

import com.example.mymovie2019.data.local.database.entity.MovieEntity
import com.example.mymovie2019.data.local.model.MovieItem
import com.example.mymovie2019.data.local.model.MovieType
import com.example.mymovie2019.data.remote.response.MoviesResponse
import com.example.mymovie2019.data.repository.movie.MovieRepository
import com.example.mymovie2019.domains.base.BaseRemoteUseCase
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.utils.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope

class GetMoviesUseCase(
    uiScope: CoroutineScope,
    private val movieRepository: MovieRepository,
    callBack: InteractionWithUICallback
) :
    BaseRemoteUseCase<Pair<Int, MovieType>, MoviesResponse, List<MovieItem>>(uiScope, callBack) {


    override suspend fun execute(parameter: Pair<Int, MovieType>): MoviesResponse {
        val (page, movieType) = parameter
        val response = movieRepository.getMoviesAsync(page, movieType)
        response.movieType = parameter.second
        return response
    }

    override fun createNetworkBoundResource(): NetworkBoundResource<Pair<Int,MovieType>,MoviesResponse, List<MovieItem>> =
        object : NetworkBoundResource<Pair<Int, MovieType>,MoviesResponse, List<MovieItem>>() {

            override fun isShowLoading(): Boolean {
                return false
            }

            override fun loadFromLocal(param: Pair<Int, MovieType>): List<MovieItem> {
                val page = param.first
                val movieType = param.second
                val movieEntities = movieRepository.getMovies(page,movieType)
                return movieEntities.map {
                    MovieItem(it.id, it.name,it.releaseDate, it.rating, it.imageUrl)
                }
            }

            override fun saveToLocal(response: MoviesResponse) {
                val movieRemotes = response.movieRemotes
                val movieEntities = movieRemotes.map {
                    MovieEntity(
                        it.id,
                        it.name,
                        it.imageUrl,
                        it.rating,
                        it.releaseDate,
                        response.movieType.name,
                        response.page
                    )
                }
                movieRepository.saveMovies(movieEntities)
            }

            override fun isSavedToLocal(param: Pair<Int, MovieType>): Boolean {
                val page = param.first
                val movieType = param.second
                val movieCount = movieRepository.countMovieEntities(page,movieType)
                return movieCount != 0L
            }

            override fun mapFrom(response: MoviesResponse): List<MovieItem> {
                val movieRemotes = response.movieRemotes
                return movieRemotes.map {
                    MovieItem(it.id, it.name, it.releaseDate, it.rating, it.imageUrl)
                }
            }

        }


}