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
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.GroupieMovieItem
import com.example.mymovie2019.ui.base.BaseFragment
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.ui.main.MainViewModel
import com.example.mymovie2019.ui.moviedetail.MovieDetailActivity
import com.example.mymovie2019.utils.MyGridDividerItemDecoration
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_see_more_movies.*
import javax.inject.Inject

class SeeMoreMovieFragment : BaseFragment() {

   private val args: SeeMoreMovieFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SeeMoreMovieViewModel
    private lateinit var movieGroupieAdapter: GroupAdapter<ViewHolder>

    override fun injectDependencies(myApplication: MyApplication) =  myApplication.appComponent.inject(this)

    override fun setupBundle() {
        viewModel.moviePage = args.page
        viewModel.movieItems = args.movieItem
    }

    override fun createFragmentViewModel(): BaseViewModel {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SeeMoreMovieViewModel::class.java]
        return viewModel
    }

    override fun bindActivityViewModel(): BaseViewModel = ViewModelProviders.of(requireActivity())[MainViewModel::class.java]

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_see_more_movies,container,false)
    }

    override fun setupUIComponents() {
        recycler_view_grid_movie.layoutManager = GridLayoutManager(requireActivity(), 3)
        recycler_view_grid_movie.addItemDecoration(MyGridDividerItemDecoration(16,3))
        movieGroupieAdapter = GroupAdapter()
        recycler_view_grid_movie.adapter = movieGroupieAdapter
    }

    override fun setupUIEvents() {
        subscribeMovieItems()
        viewModel.loadMovieItems()
        //
        movieGroupieAdapter.setOnItemClickListener { item, view ->
            if (item is GroupieMovieItem) {
                val movieTransfer = viewModel.getMovieTransfers(item.movie)
                val shareViewsPair = getSharedViewOfRecyclerViewMovies(movieGroupieAdapter.getAdapterPosition(item))
                val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), *shareViewsPair)
                val intent = Intent(requireActivity(), MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.BUNDLE_MOVIE_DETAIL, movieTransfer)
                startActivity(intent, optionsCompat.toBundle())
            }
        }


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

    private fun subscribeMovieItems() {
        viewModel.movieGroupieItems.observe(viewLifecycleOwner, Observer {
            movieGroupieAdapter.addAll(it)
        })
    }
}