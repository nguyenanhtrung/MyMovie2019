package com.example.mymovie2019.ui.listmovie

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.ui.base.BaseFragment
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.ui.castdetail.CastDetailViewModel
import kotlinx.android.synthetic.main.fragment_list_movie.*
import java.util.*
import javax.inject.Inject

class ListMovieFragment : BaseFragment(), ListMovieVerticalAdapter.OnClickMoveItemListener {

    companion object {
        private const val KEY_BUNDLE_LIST_MOVIE = "ListMovieItem"

        fun newInstance(movieItems: MutableList<MovieTransfer>): ListMovieFragment {
            val bundle = Bundle()
            bundle.putParcelableArrayList(KEY_BUNDLE_LIST_MOVIE, movieItems as ArrayList<out Parcelable>)
            val listMovieFragment = ListMovieFragment()
            listMovieFragment.arguments = bundle
            return listMovieFragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val listMovieViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ListMovieViewModel::class.java]
    }

    private lateinit var listMovieVerticalAdapter: ListMovieVerticalAdapter

    override fun injectDependencies(myApplication: MyApplication) {
        myApplication.appComponent.inject(this)
    }

    override fun createFragmentViewModel(): BaseViewModel = listMovieViewModel

    override fun bindActivityViewModel(): BaseViewModel {
        return ViewModelProviders.of(requireActivity())[CastDetailViewModel::class.java]
    }

    override fun setupBundle() {
        val bundle = arguments
        bundle?.let {
            val movieItems: MutableList<MovieTransfer>? = it.getParcelableArrayList(KEY_BUNDLE_LIST_MOVIE)
            listMovieViewModel.movieItems = movieItems
        }
    }

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_list_movie,container,false)
    }

    override fun setupUIComponents() {
        setupMoviesRecyclerView()
    }

    private fun setupMoviesRecyclerView() {
        recycler_view_movies_vertical.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            if (!::listMovieVerticalAdapter.isInitialized) {
                listMovieVerticalAdapter = ListMovieVerticalAdapter(this@ListMovieFragment)
            }
            adapter = listMovieVerticalAdapter
        }
    }


    //On Movie Item Click
    override fun onItemClick(view: View?, position: Int) {
    }

    private fun showListMovie() {
        val movieItems = listMovieViewModel.movieItems
        movieItems?.let {
            listMovieVerticalAdapter.submitList(it)
        }
    }

    override fun setupUIEvents() {
        showListMovie()
    }
}