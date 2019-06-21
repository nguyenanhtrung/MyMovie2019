package com.example.mymovie2019.domains.tvshow

import com.example.mymovie2019.data.local.database.entity.GenreEntity
import com.example.mymovie2019.data.local.model.TvShowGroupieItem
import com.example.mymovie2019.data.local.model.TvShowType
import com.example.mymovie2019.data.remote.response.Genre
import com.example.mymovie2019.data.remote.response.TvShowsResponse
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.data.repository.tvshow.TvShowRepository
import com.example.mymovie2019.domains.base.BaseRemoteUseCase
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.utils.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope

class GetTvShowsUseCase(
    uiScope: CoroutineScope,
    private val tvShowRepository: TvShowRepository,
    private val genreRepository: GenreRepository,
    interactionWithUICallback: InteractionWithUICallback
) : BaseRemoteUseCase<Pair<Int, TvShowType>, TvShowsResponse, List<TvShowGroupieItem>>(
    uiScope,
    interactionWithUICallback
) {


    override suspend fun execute(parameter: Pair<Int, TvShowType>): TvShowsResponse {
        return tvShowRepository.getTvShowsAsync(parameter.first, parameter.second)
    }

    override fun createNetworkBoundResource(): NetworkBoundResource<in Pair<Int, TvShowType>, TvShowsResponse, List<TvShowGroupieItem>> =
            object : NetworkBoundResource<Pair<Int, TvShowType>, TvShowsResponse, List<TvShowGroupieItem>>() {
                override fun loadFromLocal(param: Pair<Int, TvShowType>): List<TvShowGroupieItem> {

                }

                override fun saveToLocal(response: TvShowsResponse) {
                    val tvShowRemotes = response.tvShowRemotes




                }

                override fun isSavedToLocal(param: Pair<Int, TvShowType>): Boolean =
                    tvShowRepository.checkTvShowsExist(param.first, param.second)

                override fun mapFrom(response: TvShowsResponse): List<TvShowGroupieItem> {
                }

            }

}