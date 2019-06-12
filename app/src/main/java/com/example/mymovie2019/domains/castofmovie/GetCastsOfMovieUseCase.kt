package com.example.mymovie2019.domains.castofmovie

import com.example.mymovie2019.data.remote.response.Cast
import com.example.mymovie2019.data.remote.response.MovieCreditResponse
import com.example.mymovie2019.data.repository.cast.CastRepository
import com.example.mymovie2019.domains.base.BaseRemoteUseCase
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.utils.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope

class GetCastsOfMovieUseCase(
    uiScope: CoroutineScope,
    private val castRepository: CastRepository,
    interactionWithUICallback: InteractionWithUICallback
) : BaseRemoteUseCase<Int, MovieCreditResponse, Cast>(uiScope, interactionWithUICallback) {

    override suspend fun execute(parameter: Int): MovieCreditResponse {
        return castRepository.getCastsOfMovieAsync(parameter).await()
    }

    override fun createNetworkBoundResource(): NetworkBoundResource<in Int, MovieCreditResponse, Cast>
        = object : NetworkBoundResource<Int, MovieCreditResponse, Cast>() {

        override fun loadFromLocal(param: Int): Cast {
        }

        override fun saveToLocal(response: MovieCreditResponse) {
        }

        override fun isSavedToLocal(param: Int): Boolean {

        }

        override fun mapFrom(response: MovieCreditResponse): Cast {
        }
    }

}