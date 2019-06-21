package com.example.mymovie2019.domains.genres

import com.example.mymovie2019.data.local.database.entity.GenreEntity
import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.response.Genre
import com.example.mymovie2019.data.remote.response.GenreResponse
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.domains.base.BaseRemoteUseCase
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.utils.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope

class SaveGenresToLocalUseCase (uiScope: CoroutineScope,
                                private val genreRepository: GenreRepository,
                                callBack: InteractionWithUICallback
) : BaseRemoteUseCase<GenreCategory, GenreResponse, Unit>(uiScope, callBack) {


    override suspend fun execute(parameter: GenreCategory): GenreResponse {
        val genreResponse = genreRepository.getGenresFromServer(parameter)
        genreResponse.genreCategory = parameter.categoryName
        return genreResponse
    }

    override fun createNetworkBoundResource(): NetworkBoundResource<GenreCategory, GenreResponse, Unit> =
        object : NetworkBoundResource<GenreCategory, GenreResponse, Unit>() {

            override fun isShowLoading(): Boolean {
                return false
            }

            override fun loadFromLocal(param: GenreCategory) = Unit

            override fun saveToLocal(response: GenreResponse) {
                val genres = response.genres
                val genreLocals = mapToGenreLocals(genres, response.genreCategory)
                genreRepository.saveGenres(genreLocals)
            }

            override fun isSavedToLocal(param: GenreCategory): Boolean {
                val genreCount = genreRepository.countMovieGenres(param.categoryName)
                return genreCount > 0
            }

            override fun mapFrom(response: GenreResponse) = Unit

            override fun shouldLoadFromLocal(): Boolean = false
        }

    private fun mapToGenreLocals(genres: List<Genre>, genreCategory: String): List<GenreEntity> = genres.map {
        GenreEntity(it.id.toLong(), it.name, genreCategory)
    }
}