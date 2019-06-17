package com.example.mymovie2019.ui.casts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovie2019.data.local.model.CastItem
import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.data.local.model.Event
import com.example.mymovie2019.data.local.model.ItemType
import com.example.mymovie2019.data.remote.response.CastsResponse
import com.example.mymovie2019.data.repository.cast.CastRepository
import com.example.mymovie2019.domains.casts.GetCastsUseCase
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

    private val getCastsUseCase by lazy {
        GetCastsUseCase(this, castRepository, this)
    }

    private var currentCastPage = 1
    var currentPosCastSelected = -1

    internal fun loadPopularCasts() {
        getCastsUseCase.invoke(currentCastPage) {
            _castsLiveData.value = it.toMutableList()
        }
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
            getCastsUseCase.invoke(currentCastPage) {
                hideLoadingMoreCasts()
                val copyPopularCasts = getCopyPopularCasts()
                copyPopularCasts.addAll(it)
                _castsLiveData.value = copyPopularCasts
            }
        }
    }

    private fun showLoadingMoreCasts() {
        val copyPopularCasts = getCopyPopularCasts()
        if (copyPopularCasts.isEmpty()) {
            return
        }
        copyPopularCasts.add(CastItem(itemType = ItemType.LOAD_MORE))
        _castsLiveData.value = copyPopularCasts
    }

    private fun hideLoadingMoreCasts() {
        val copyPopularCasts = getCopyPopularCasts()
        if (copyPopularCasts.isEmpty()) {
            return
        }
        copyPopularCasts.remove(CastItem(itemType = ItemType.LOAD_MORE))
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