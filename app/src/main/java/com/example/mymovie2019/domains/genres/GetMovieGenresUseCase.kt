package com.example.mymovie2019.domains.genres

import com.example.mymovie2019.data.local.database.entity.GenreLocal
import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.response.Genre
import com.example.mymovie2019.data.remote.response.GenreResponse
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.domains.base.BaseRemoteUseCase
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.utils.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope

class GetMovieGenresUseCase(
    uiScope: CoroutineScope,
    private val genreRepository: GenreRepository,
    callBack: InteractionWithUICallback
) : BaseRemoteUseCase<Unit, GenreResponse, List<GenreLocal>>(uiScope, callBack) {


    override suspend fun execute(parameter: Unit): GenreResponse {
        return genreRepository.getGenresMovieFromServer()
    }

    override fun createNetworkBoundResource(): NetworkBoundResource<Unit,GenreResponse, List<GenreLocal>> =
        object : NetworkBoundResource<Unit,GenreResponse, List<GenreLocal>>() {

            override fun isShowLoading(): Boolean {
                return false
            }

            override fun loadFromLocal(param: Unit): List<GenreLocal> = genreRepository.getGenres(GenreCategory.MOVIE)

            override fun saveToLocal(response: GenreResponse) {
                val genres = response.genres
                val genreLocals =  mapToGenreLocals(genres)
                genreRepository.saveGenres(genreLocals)
            }


            override fun isSavedToLocal(param: Unit): Boolean {
                val genreCount = genreRepository.countMovieGenres(GenreCategory.MOVIE.categoryName)
                return genreCount > 0
            }

            override fun mapFrom(response: GenreResponse): List<GenreLocal> {
                val genres = response.genres
                return mapToGenreLocals(genres)
            }

            override fun shouldLoadFromLocal(): Boolean = false
        }

    private fun mapToGenreLocals(genres: List<Genre>): List<GenreLocal> = genres.map {
        GenreLocal(it.id.toLong(), it.name, GenreCategory.MOVIE.categoryName)
    }


}