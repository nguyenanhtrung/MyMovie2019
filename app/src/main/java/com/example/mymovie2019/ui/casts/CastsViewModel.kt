package com.example.mymovie2019.ui.casts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovie2019.data.local.model.CastItem
import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.data.local.model.Event
import com.example.mymovie2019.data.local.model.ItemType
import com.example.mymovie2019.data.remote.response.CastsResponse
import com.example.mymovie2019.data.repository.cast.CastRepository
import com.example.mymovie2019.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CastsViewModel @Inject constructor(private val castRepository: CastRepository): BaseViewModel() {

    private val _castsLiveData by lazy {
        MutableLiveData<MutableList<CastItem>>()
    }
    internal val castsLiveData: LiveData<MutableList<CastItem>>
        get() = _castsLiveData

    private val _navigateToDetail by lazy {
        MutableLiveData<Event<CastTransfer>>()
    }
    internal val navigateToDetail: LiveData<Event<CastTransfer>>
        get() = _navigateToDetail

    private var currentCastPage = 1
    var currentPosCastSelected = -1

    internal fun loadPopularCasts() {
        launch {
            showLoading()
            val castsResponse = castRepository.getPopularCastsAsync(currentCastPage).await()
            hideLoading()
            val castItems = parseCastsRemoteToCastItems(castsResponse)
            showPopularCasts(castItems)
        }
    }

    private fun parseCastsRemoteToCastItems(castsResponse: CastsResponse): MutableList<CastItem> {
        val castsRemote = castsResponse.castRemotes
        val castItems = castsRemote.map {
            CastItem(it.id, it.name, it.profilePath)
        }
        return castItems.toMutableList()
    }

    private fun showPopularCasts(castItems: MutableList<CastItem>) {
        _castsLiveData.value = castItems
    }

    private fun getCopyPopularCasts(): MutableList<CastItem> {
        val originalPopularCasts = _castsLiveData.value ?: return mutableListOf()
        val copyPopularCats = mutableListOf<CastItem>()
        for (index in 0 until originalPopularCasts.size) {
            val castItem = CastItem(originalPopularCasts[index])
            copyPopularCats += castItem
        }
        return copyPopularCats

    }

    fun onLoadMorePopularCast(page: Int) {
        launch {
            currentCastPage = page + 1
            showLoadingMoreCasts()
            val castsResponse = castRepository.getPopularCastsAsync(currentCastPage).await()
            hideLoadingMoreCasts()
            val castItems = parseCastsRemoteToCastItems(castsResponse)
            val copyPopularCasts = getCopyPopularCasts()
            copyPopularCasts.addAll(castItems)
            _castsLiveData.value = copyPopularCasts
        }
    }

    private fun showLoadingMoreCasts() {
        val copyPopularCasts = getCopyPopularCasts()
        if (copyPopularCasts.isEmpty()) {
            return
        }
        copyPopularCasts.add(CastItem(itemType = ItemType.ItemLoading))
        _castsLiveData.value = copyPopularCasts
    }

    private fun hideLoadingMoreCasts() {
        val copyPopularCasts = getCopyPopularCasts()
        if (copyPopularCasts.isEmpty()) {
            return
        }
        copyPopularCasts.remove(CastItem(itemType = ItemType.ItemLoading))
        _castsLiveData.value = copyPopularCasts
    }

    fun onCastItemClick(position: Int) {
        currentPosCastSelected = position
        val casts = _castsLiveData.value
        casts?.let {
            val castItemSelected = it[currentPosCastSelected]
            val castTransfer = CastTransfer(castItemSelected.id, castItemSelected.name, castItemSelected.imagePath)
            _navigateToDetail.value = Event(castTransfer)
        }
    }
}