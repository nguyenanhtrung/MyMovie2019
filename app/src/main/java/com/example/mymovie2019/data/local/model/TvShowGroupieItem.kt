package com.example.mymovie2019.data.local.model

import com.example.mymovie2019.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_tv_show_detail.*
import kotlinx.android.synthetic.main.item_tv_shows.*

class TvShowGroupieItem(private val tvShow: TvShow) : Item() {

    override fun getLayout(): Int = R.layout.item_tv_shows

    override fun bind(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            text_name_tv_show.text = tvShow.name
            text_categories_tv_show.text = tvShow.genres.joinToString("|")
            text_rating.text = (tvShow.rating / 2).toString()
        }
    }
}