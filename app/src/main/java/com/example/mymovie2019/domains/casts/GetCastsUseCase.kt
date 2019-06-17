package com.example.mymovie2019.domains.casts

import com.example.mymovie2019.data.local.model.CastItem
import com.example.mymovie2019.data.remote.response.CastsResponse
import com.example.mymovie2019.data.repository.cast.CastRepository
import com.example.mymovie2019.domains.base.BaseRemoteUseCase
import com.example.mymovie2019.ui.base.InteractionWithUICallback
import com.example.mymovie2019.utils.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope

class GetCastsUseCase(
    uiScope: CoroutineScope,
    private val castRepository: CastRepository,
    callback: InteractionWithUICallback
) : BaseRemoteUseCase<Int, CastsResponse, List<CastItem>>(uiScope, callback) {


    override suspend fun execute(parameter: Int): CastsResponse {
        return castRepository.getPopularCastsAsync(parameter).await()
    }

    override fun createNetworkBoundResource(): NetworkBoundResource<in Int, CastsResponse, List<CastItem>> =
        object : NetworkBoundResource<Int, CastsResponse, List<CastItem>>() {
            override fun isShowLoading(): Boolean = false

            override fun loadFromLocal(param: Int): List<CastItem>  = castRepository.getCasts(param)

            override fun saveToLocal(response: CastsResponse) = castRepository.saveCastsFromCastRemotes(response.castRemotes)

            override fun isSavedToLocal(param: Int): Boolean = castRepository.isCastsSaved(param)

            override fun mapFrom(response: CastsResponse): List<CastItem> {
                val castRemotes = response.castRemotes
                return castRemotes.map {
                    CastItem(it.id, it.name, it.profilePath)
                }
            }
        }

}