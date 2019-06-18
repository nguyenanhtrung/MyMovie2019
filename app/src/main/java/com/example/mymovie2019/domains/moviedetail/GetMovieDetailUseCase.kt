package com.example.mymovie2019.domains.moviedetail

import com.example.mymovie2019.data.local.model.MovieAbout
import com.example.mymovie2019.data.local.model.MovieDetail
import com.example.mymovie2019.data.remote.response.MovieDetailResponse
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.data.repository.moviedetail.MovieDetailRepository
import com.example.mymovie2019.domains.base.BaseRemoteUseCase
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.utils.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

class GetMovieDetailUseCase(
        uiScope: CoroutineScope,
        private val movieDetailRepository: MovieDetailRepository,
        private val genreRepository: GenreRepository,
        callBack: InteractionWithUICallback
) : BaseRemoteUseCase<Int, MovieDetailResponse, MovieDetail>(uiScope, callBack) {


    override suspend fun execute(parameter: Int): MovieDetailResponse {
        return movieDetailRepository.getMovieDetailAsync(movieId = parameter).await()
    }

    override fun createNetworkBoundResource(): NetworkBoundResource<in Int, MovieDetailResponse, MovieDetail> =
            object : NetworkBoundResource<Int, MovieDetailResponse, MovieDetail>() {
                override fun isShowLoading(): Boolean = false

                override fun loadFromLocal(param: Int): MovieDetail {
                    return movieDetailRepository.getMovieDetail(param)
                }

                override fun saveToLocal(response: MovieDetailResponse) {
                    val genreIds = response.genres?.map {
                        it.id.toLong()
                    }
                    genreRepository.updateMovieDetailId(response.id!!.toLong(),genreIds)
                    movieDetailRepository.saveMovieDetail(response)
                }

                override fun isSavedToLocal(param: Int): Boolean {
                    return movieDetailRepository.checkExistsMovieDetail(param)
                }

                override fun mapFrom(response: MovieDetailResponse): MovieDetail {
                    return MovieDetail(
                        response.backdropPath,
                        MovieAbout(
                            response.overview,
                            response.originalTitle,
                            response.status,
                            response.runtime,
                            response.genres,
                            response.releaseDate,
                            response.budget?.toLong(),
                            response.revenue?.toLong(),
                            response.homepage
                        )
                    )
                }
            }

}