package com.example.mymovie2019.domains.genres

import com.example.mymovie2019.data.remote.response.GenreResponse
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.domains.base.BaseRemoteUseCase
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import javax.inject.Inject

class GetMovieGenresRemoteUseCase @Inject constructor(callBack: InteractionWithUICallback,
                                                      private val genreRepository: GenreRepository) : BaseRemoteUseCase<Unit, GenreResponse>(callBack) {


    override suspend fun execute(parameter: Unit): GenreResponse {
        return genreRepository.getGenresMovieFromServer()
    }
}