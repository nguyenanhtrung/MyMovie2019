package com.example.mymovie2019.domains.moviedetail

import com.example.mymovie2019.data.local.model.MovieDetail
import com.example.mymovie2019.data.remote.response.MovieDetailResponse
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.data.repository.movie.MovieRepository
import com.example.mymovie2019.domains.base.BaseRemoteUseCase
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.utils.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope

class GetMovieDetailUseCase(
        uiScope: CoroutineScope,
        private val movieRepository: MovieRepository,
        private val genreRepository: GenreRepository,
        callBack: InteractionWithUICallback
) : BaseRemoteUseCase<Int, MovieDetailResponse, MovieDetail>(uiScope, callBack) {


    override suspend fun execute(parameter: Int): MovieDetailResponse {
        return movieRepository.getMovieDetailAsync(movieId = parameter).await()
    }

    override fun createNetworkBoundResource(): NetworkBoundResource<in Int, MovieDetailResponse, MovieDetail> =
            object : NetworkBoundResource<Int, MovieDetailResponse, MovieDetail>() {
                override fun isShowLoading(): Boolean = false

                override fun loadFromLocal(param: Int): MovieDetail {
                }

                override fun saveToLocal(response: MovieDetailResponse) {

                }

                override fun isSavedToLocal(param: Int): Boolean {
                }

                override fun mapFrom(response: MovieDetailResponse): MovieDetail {
                }
            }

}