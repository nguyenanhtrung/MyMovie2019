package com.example.mymovie2019.ui.seemoremovie

import android.content.Intent
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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R

import com.example.mymovie2019.ui.base.BaseListMovieFragment
import com.example.mymovie2019.ui.base.BaseListMovieViewModel
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.ui.main.MainViewModel

import kotlinx.android.synthetic.main.fragment_see_more_movies.*
import javax.inject.Inject

class SeeMoreMovieFragment : BaseListMovieFragment() {


    private val args: SeeMoreMovieFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SeeMoreMovieViewModel

    override fun injectDependencies(myApplication: MyApplication) =  myApplication.appComponent.inject(this)

    override fun createFragmentViewModel(): BaseViewModel {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SeeMoreMovieViewModel::class.java]
        return viewModel
    }

    override fun bindActivityViewModel(): BaseViewModel = ViewModelProviders.of(requireActivity())[MainViewModel::class.java]

    override fun setupBundle() {
        viewModel.page = args.page
        viewModel.movieItems = args.movieItem
        viewModel.movieType = args.movieType
    }

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_see_more_movies,container,false)
    }

    override fun getBaseListMovieViewModel(): BaseListMovieViewModel = viewModel

    override fun getMoviesRecyclerView(): RecyclerView = recycler_view_grid_movie


}