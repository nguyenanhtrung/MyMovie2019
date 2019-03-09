package com.example.mymovie2019.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mymovie2019.R

fun ImageView.loadImageByUrl(url: String) {

    val loadingView = CircularProgressDrawable(context)
    loadingView.strokeWidth = 5f
    loadingView.centerRadius = 30f
    loadingView.setColorSchemeColors(ContextCompat.getColor(context,R.color.colorSecondary))
    loadingView.start()
    val requestOption = RequestOptions.placeholderOf(loadingView)

    Glide.with(this)
        .load(url)
        .apply(requestOption)
        .into(this)
}