package com.example.mymovie2019.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mymovie2019.R

fun ImageView.loadImageByUrl(url: String, width: Int = 150, height: Int = 150) {


    val requestOption = RequestOptions()
        .override(width, height)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    Glide.with(this)
        .load(url)
        .apply(requestOption)
        .into(this)
}