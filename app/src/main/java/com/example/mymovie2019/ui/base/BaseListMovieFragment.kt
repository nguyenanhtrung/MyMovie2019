package com.example.mymovie2019.ui.base

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie2019.utils.MyGridDividerItemDecoration
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_see_more_movies.*
import javax.inject.Inject

abstract class BaseListMovieFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val baseViewModel by lazy {
        getBaseListMovieViewModel()
    }
    protected val recyclerViewMovies by lazy {
        getMoviesRecyclerView()
    }
    private lateinit var movieGroupieAdapter: GroupAdapter<ViewHolder>


    override fun setupUIComponents() {
        recycler_view_grid_movie.layoutManager = GridLayoutManager(requireActivity(), 3)
        recycler_view_grid_movie.addItemDecoration(MyGridDividerItemDecoration(16,3))
        movieGroupieAdapter = GroupAdapter()
        recycler_view_grid_movie.adapter = movieGroupieAdapter
    }

    override fun setupUIEvents() {
        subscribeMovieItems()
    }

    private fun subscribeMovieItems() {
        baseViewModel.movieGroupieItems.observe(viewLifecycleOwner, Observer {
            movieGroupieAdapter.addAll(it)
        })
    }

    abstract fun getBaseListMovieViewModel(): BaseListMovieViewModel
    abstract fun getMoviesRecyclerView(): RecyclerView
}