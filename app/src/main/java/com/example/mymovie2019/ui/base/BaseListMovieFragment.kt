package com.example.mymovie2019.ui.base

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.GroupieMovieItem
import com.example.mymovie2019.ui.moviedetail.MovieDetailActivity
import com.example.mymovie2019.utils.EndlessScrollListener
import com.example.mymovie2019.utils.MyGridDividerItemDecoration
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_see_more_movies.*

abstract class BaseListMovieFragment : BaseFragment() {

    protected val baseViewModel by lazy {
        getBaseListMovieViewModel()
    }
    private val recyclerViewMovies by lazy {
        getMoviesRecyclerView()
    }
    private lateinit var movieGroupieAdapter: GroupAdapter<ViewHolder>
    private lateinit var endlessScrollListener: EndlessScrollListener

    override fun setupUIComponents() {
        recyclerViewMovies.layoutManager = GridLayoutManager(requireActivity(), 3)
        recyclerViewMovies.addItemDecoration(MyGridDividerItemDecoration(16, 3))
        movieGroupieAdapter = GroupAdapter()
        recyclerViewMovies.adapter = movieGroupieAdapter
    }

    override fun setupUIEvents() {
        subscribeMovieItems()
        setupScrollEventRecyclerViewMovies()
        baseViewModel.showMovies()

        //
        movieGroupieAdapter.setOnItemClickListener { item, view ->
            if (item is GroupieMovieItem) {
                val movieTransfer = baseViewModel.getMovieTransfers(item.movie)
                val shareViewsPair = getSharedViewOfRecyclerViewMovies(movieGroupieAdapter.getAdapterPosition(item))
                val optionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), *shareViewsPair)
                val intent = Intent(requireActivity(), MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.BUNDLE_MOVIE_DETAIL, movieTransfer)
                startActivity(intent, optionsCompat.toBundle())
            }
        }
        //

    }

    private fun setupScrollEventRecyclerViewMovies() {
        if (!::endlessScrollListener.isInitialized) {
            endlessScrollListener = object : EndlessScrollListener(recyclerViewMovies.layoutManager as GridLayoutManager, baseViewModel.page) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    baseViewModel.onLoadMoreMovies(page)
                }

            }
        }
        recyclerViewMovies.addOnScrollListener(endlessScrollListener)
    }

    private fun subscribeMovieItems() {
        baseViewModel.movieGroupieItems.observe(viewLifecycleOwner, Observer {
            movieGroupieAdapter.addAll(it)
        })
    }

    private fun getSharedViewOfRecyclerViewMovies(position: Int) : Array< out Pair<View, String>> {
        val layoutManager = recycler_view_grid_movie.layoutManager
        val itemView = layoutManager?.findViewByPosition(position) ?: return arrayOf()
        val textName = itemView.findViewById<TextView>(R.id.text_movie_name)
        val pairName = Pair<View, String>(textName, getString(R.string.text_movie_name_transition))
        val imageMovie = itemView.findViewById<ImageView>(R.id.image_movie_horizontal)
        val pairMovie = Pair<View, String>(imageMovie , getString(R.string.image_movie_transition_name))
        val textDate = itemView.findViewById<TextView>(R.id.text_release_date)
        val pairDate = Pair<View, String>(textDate, getString(R.string.text_release_date_transition))
        return  arrayOf(pairName, pairMovie, pairDate)
    }

    abstract fun getBaseListMovieViewModel(): BaseListMovieViewModel
    abstract fun getMoviesRecyclerView(): RecyclerView
}