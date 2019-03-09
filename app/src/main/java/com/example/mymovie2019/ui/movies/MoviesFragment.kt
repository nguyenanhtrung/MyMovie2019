package com.example.mymovie2019.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.ui.base.BaseFragment
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject


class MoviesFragment : BaseFragment(), MovieTypesAdapter.OnLoadMoreMovieItemListener {

    companion object {
        const val TAG = "MoviesFragment"

        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val moviesViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[MoviesViewModel::class.java]
    }

    private val activityViewModel by lazy {
        ViewModelProviders.of(requireActivity())[MainViewModel::class.java]
    }

    private lateinit var sliderMovieImageAdapter: ImageSliderAdapter
    private lateinit var moviesTypeAdapter: MovieTypesAdapter


    override fun injectDependencies(myApplication: MyApplication) {
        val appComponent = myApplication.appComponent
        appComponent.inject(this)
    }

    override fun createFragmentViewModel(): BaseViewModel = moviesViewModel
    override fun bindActivityViewModel(): BaseViewModel = activityViewModel

    override fun setupBundle() {

    }

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(com.example.mymovie2019.R.layout.fragment_movies, container, false)
    }

    override fun setupUIComponents() {
        setupMoviesTypeRecyclerView()
    }


    private fun setupMoviesTypeRecyclerView() {
        val linearLayoutManager = object : LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        recycler_view_vertical_movies.layoutManager = linearLayoutManager

        if (!::moviesTypeAdapter.isInitialized) {
            moviesTypeAdapter = MovieTypesAdapter(this@MoviesFragment)
        }
        recycler_view_vertical_movies.adapter = moviesTypeAdapter
    }

    override fun setupUIEvents() {
        subscribeListMoviesType()
        subscribeSliderMovies()
        subscribePageMovieSlider()
        moviesViewModel.showListMoviesType()
        moviesViewModel.loadAllMovies()
    }

    private fun subscribePageMovieSlider() {
        moviesViewModel.pageSliderMovieLiveData.observe(viewLifecycleOwner, Observer {
            view_pager_slider_image.currentItem = it
        })
    }

    private fun subscribeSliderMovies() {
        moviesViewModel.sliderMoviesLiveData.observe(viewLifecycleOwner, Observer {
            if (!::sliderMovieImageAdapter.isInitialized) {
                sliderMovieImageAdapter = ImageSliderAdapter(requireActivity(), it)
            }
            view_pager_slider_image.adapter = sliderMovieImageAdapter
            circle_indicator.setViewPager(view_pager_slider_image)
        })
    }

    override fun onLoadMoreMovie(page: Int, listPosition: Int) {
        moviesViewModel.onLoadMoreMovies(page, listPosition)
    }

    private fun subscribeListMoviesType() {
        moviesViewModel.moviesTypeLiveData.observe(viewLifecycleOwner, Observer {
            moviesTypeAdapter.submitList(it)
        })
    }

}