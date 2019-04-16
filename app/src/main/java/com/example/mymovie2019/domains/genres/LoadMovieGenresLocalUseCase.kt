package com.example.mymovie2019.domains.genres

import com.example.mymovie2019.data.local.database.entity.GenreLocal
import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.response.Genre
import com.example.mymovie2019.data.remote.response.GenreResponse
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.domains.base.BaseLocalUseCase
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.ui.base.UseCaseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoadMovieGenresLocalUseCase constructor(
    private val genreRepository: GenreRepository,
    private val callBack: InteractionWithUICallback
) : BaseLocalUseCase<Unit, Unit>() {

    private val getMovieGenresRemoteUseCase: GetMovieGenresRemoteUseCase =
        GetMovieGenresRemoteUseCase(callBack,genreRepository)

    override suspend fun execute(parameter: Unit) {
        val genreCount = withContext(Dispatchers.IO) {
            genreRepository.countMovieGenres(GenreCategory.MOVIE.categoryName)
        }
        if (genreCount == 0L) {
            getMovieGenresRemoteUseCase.invoke(Unit, object : UseCaseHandler<GenreResponse> {
                override fun onSuccess(result: GenreResponse) {
                    val genres = result.genres
                    saveGenres(genres)
                }
            })
        }
    }

    private fun saveGenres(genres: List<Genre>) {
        launch {
            val genreLocals = genres.map {
                GenreLocal(it.id.toLong(), it.name, GenreCategory.MOVIE.categoryName)
            }
            withContext(Dispatchers.IO) {
                genreRepository.saveGenres(genreLocals)
            }
        }
    }
}