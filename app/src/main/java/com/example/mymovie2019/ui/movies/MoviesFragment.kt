package com.example.mymovie2019.ui.movies

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.ui.base.BaseFragment
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.ui.main.MainViewModel
import com.example.mymovie2019.ui.moviedetail.MovieDetailActivity
import com.example.mymovie2019.ui.moviedetail.MovieDetailActivity.Companion.BUNDLE_MOVIE_DETAIL
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject


class MoviesFragment : BaseFragment(), MovieTypesAdapter.OnLoadMoreMovieItemListener,
    ImageSliderAdapter.OnClickMovieSliderItemListener,
    MovieTypesAdapter.OnClickItemMovieHorizontalListener,
    MovieGenresAdapter.OnClickGenreItemListener{


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
    private lateinit var genresAdapter: MovieGenresAdapter


    override fun injectDependencies(myApplication: MyApplication) {
        val appComponent = myApplication.appComponent
        appComponent.inject(this)
    }

    override fun createFragmentViewModel(): BaseViewModel = moviesViewModel
    override fun bindActivityViewModel(): BaseViewModel = activityViewModel

    override fun setupBundle(){}

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun setupUIComponents() {
        setupSliderMovieViewPager()
        setupMoviesTypeRecyclerView()
        setupGenresRecyclerView()
    }

    private fun setupGenresRecyclerView() {
        recycler_view_categories.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL,false)
        if (!::genresAdapter.isInitialized) {
            genresAdapter = MovieGenresAdapter(this)
        }
        recycler_view_categories.adapter = genresAdapter
    }

    override fun onGenreItemClick(view: View?, position: Int) {

    }

    private fun subscribeGenreMovies() {
        moviesViewModel.genresLiveData.observe(this, Observer {
            genresAdapter.submitList(it)
        })
    }

    private fun setupSliderMovieViewPager() {
        view_pager_slider_image.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                recycler_view_vertical_movies.requestFocus()
            }
        }
    }

    private fun setupMoviesTypeRecyclerView() {
        val linearLayoutManager = object : LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        recycler_view_vertical_movies.layoutManager = linearLayoutManager

        if (!::moviesTypeAdapter.isInitialized) {
            moviesTypeAdapter = MovieTypesAdapter(this@MoviesFragment, this)
        }
        recycler_view_vertical_movies.adapter = moviesTypeAdapter
    }

    override fun setupUIEvents() {
        setupScrollViewEvent()
        subscribeListMoviesType()
        subscribeSliderMovies()
        subscribePageMovieSlider()
        subscribeGenreMovies()

        moviesViewModel.showListMoviesType()
        moviesViewModel.loadAllMovies()

    }

    private fun setupScrollViewEvent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scroll_view_movies.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                moviesViewModel.onScrollViewEvent(view_pager_slider_image.height,scrollY,oldScrollY)
            }
        }
    }

    private fun subscribePageMovieSlider() {
        moviesViewModel.pageSliderMovieLiveData.observe(viewLifecycleOwner, Observer {
            view_pager_slider_image.currentItem = it
        })
    }

    private fun subscribeSliderMovies() {
        moviesViewModel.sliderMoviesLiveData.observe(viewLifecycleOwner, Observer {
            if (!::sliderMovieImageAdapter.isInitialized) {
                sliderMovieImageAdapter = ImageSliderAdapter(requireActivity(), it, this@MoviesFragment)
            }
            view_pager_slider_image.adapter = sliderMovieImageAdapter
            circle_indicator.setViewPager(view_pager_slider_image)
        })
    }

    override fun onClickMovieSliderItem(view: ImageView, movieTransfer: MovieTransfer) {
        val imagePair = Pair<View,String>(view, getString(R.string.image_movie_transition_name))
        openMovieDetailWithShareElementTransition(movieTransfer, imagePair)
    }


    override fun onLoadMoreMovie(page: Int, listPosition: Int) {
        moviesViewModel.onLoadMoreMovies(page, listPosition)
    }

    private fun subscribeListMoviesType() {
        moviesViewModel.moviesTypeLiveData.observe(viewLifecycleOwner, Observer {
            moviesTypeAdapter.submitList(it)
        })
    }

    override fun onItemMovieLick(
        movieTransfer: MovieTransfer,
        imageView: ImageView,
        textName: TextView,
        textDate: TextView
        ) {
        val imagePair = Pair<View,String>(imageView,getString(R.string.image_movie_transition_name))
        val textNamePair = Pair<View,String>(textName,getString(R.string.text_movie_name_transition))
        val textDatePair = Pair<View,String>(textDate, getString(R.string.text_release_date_transition))
        openMovieDetailWithShareElementTransition(movieTransfer, imagePair,textNamePair, textDatePair)
    }

    private fun openMovieDetailWithShareElementTransition(
        movieTransfer: MovieTransfer,
        vararg shareViewPairs: Pair<View, String>
    ) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), *shareViewPairs);
        val intent = Intent(requireActivity(), MovieDetailActivity::class.java)
        intent.putExtra(BUNDLE_MOVIE_DETAIL, movieTransfer)
        startActivity(intent, options.toBundle())
    }
}