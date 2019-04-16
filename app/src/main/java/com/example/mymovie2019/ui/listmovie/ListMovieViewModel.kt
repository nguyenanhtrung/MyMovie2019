package com.example.mymovie2019.ui.listmovie

import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.ui.base.BaseViewModel
import javax.inject.Inject

class ListMovieViewModel @Inject constructor() : BaseViewModel() {

    var movieItems: MutableList<MovieTransfer>? = null
}