package com.example.mymovie2019.ui.castdetail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.ui.listmovie.ListMovieFragment

class CastDetailPagerAdapter(private val context: Context,
                             private val movieItems: MutableList<MovieTransfer>,
                             private val tvShowItems: MutableList<MovieTransfer>,
                             fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    companion object {
        private const val PAGE_COUNT = 2
    }


    override fun getItem(position: Int): Fragment = when(position) {
        0 -> ListMovieFragment.newInstance(movieItems)
        else -> ListMovieFragment.newInstance(tvShowItems)
    }

    override fun getCount(): Int = PAGE_COUNT

    override fun getPageTitle(position: Int): CharSequence? = when(position) {
        0 -> context.resources.getString(R.string.title_list_movie)
        else -> context.resources.getString(R.string.title_tv_show)
    }
}