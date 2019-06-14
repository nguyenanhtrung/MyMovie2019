package com.example.mymovie2019.domains.castofmovie

import com.example.mymovie2019.data.local.model.CharacterParam
import com.example.mymovie2019.data.remote.response.Cast
import com.example.mymovie2019.data.remote.response.MovieCreditResponse
import com.example.mymovie2019.data.repository.cast.CastRepository
import com.example.mymovie2019.data.repository.character.CharacterRepository
import com.example.mymovie2019.domains.base.BaseRemoteUseCase
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.utils.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope

class GetCastsOfMovieUseCase(
    uiScope: CoroutineScope,
    private val castRepository: CastRepository,
    private val characterRepository: CharacterRepository,
    interactionWithUICallback: InteractionWithUICallback
) : BaseRemoteUseCase<Int, MovieCreditResponse, List<Cast>>(uiScope, interactionWithUICallback) {

    override suspend fun execute(parameter: Int): MovieCreditResponse {
        return castRepository.getCastsOfMovieAsync(parameter).await()
    }

    override fun createNetworkBoundResource(): NetworkBoundResource<in Int, MovieCreditResponse, List<Cast>>
        = object : NetworkBoundResource<Int, MovieCreditResponse, List<Cast>>() {

        override fun isShowLoading(): Boolean = false

        override fun loadFromLocal(param: Int): List<Cast> {
            return castRepository.getCastLocalOfMovie(param)
        }

        override fun saveToLocal(response: MovieCreditResponse) {
            castRepository.saveCasts(response.cast)
            val characterParams = response.cast.map {
                CharacterParam(it.orderId, it.character, it.id, response.id)
            }
            characterRepository.saveCharacters(characterParams)
        }

        override fun isSavedToLocal(param: Int): Boolean {
            return characterRepository.checkExistCharsOfMovie(param)
        }

        override fun mapFrom(response: MovieCreditResponse): List<Cast> {
            return response.cast
        }
    }

}