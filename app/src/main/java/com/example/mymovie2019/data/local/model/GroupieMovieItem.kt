package com.example.mymovie2019.data.local.model

import com.example.mymovie2019.R
import com.example.mymovie2019.utils.AppKey
import com.example.mymovie2019.utils.loadImageByUrl
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_movie_horizontal.*


class GroupieMovieItem(val movie: MovieItem) : Item() {

    override fun getLayout(): Int = R.layout.item_movie_horizontal

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.text_movie_name.text = movie.name
        viewHolder.text_release_date.text = movie.releaseDate
        if (!movie.imageUrl.isNullOrEmpty()) {
            val imageUrl = "${AppKey.URL_MOVIE_ITEM_IMAGE_PATH}${movie.imageUrl}"
            viewHolder.image_movie_horizontal.loadImageByUrl(imageUrl)
        }
        val movieRating = movie.rating.toFloat()
        viewHolder.rating_bar_movie_horizontal.rating = movieRating / 2
    }


}