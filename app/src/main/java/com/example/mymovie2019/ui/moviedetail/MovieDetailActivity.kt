package com.example.mymovie2019.ui.moviedetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.data.local.model.MovieAbout
import com.example.mymovie2019.data.local.model.MovieDetail
import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.data.remote.response.Cast
import com.example.mymovie2019.ui.base.BaseActivity
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.ui.castdetail.CastDetailActivity
import com.example.mymovie2019.utils.AppKey
import com.example.mymovie2019.utils.ConvertUtils
import com.example.mymovie2019.utils.loadImageByUrl
import com.google.android.material.appbar.AppBarLayout
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.layout_content_scroll_movie_detail.*
import kotlinx.android.synthetic.main.layout_movie_info_detail.*
import javax.inject.Inject

class MovieDetailActivity : BaseActivity(), CastMovieDetailAdapter.OnClickCastItemListener {

    companion object {
        const val BUNDLE_MOVIE_DETAIL = "BundleMovieDetail"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var castMovieDetailAdapter: CastMovieDetailAdapter


    override fun injectDependencies(application: MyApplication) {
        application.appComponent.inject(this)
    }

    override fun createViewModel(): BaseViewModel {
        movieDetailViewModel = ViewModelProviders.of(this, viewModelFactory)[MovieDetailViewModel::class.java]
        return movieDetailViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        //
        onReceivedData()
        setupUiComponents()
        setupUiEvents()
    }

    private fun onReceivedData() {

        val receiverIntent = intent
        val movieDetail: MovieTransfer = receiverIntent.getParcelableExtra(BUNDLE_MOVIE_DETAIL)
        movieDetailViewModel.movieId = movieDetail.id
        showImageMovie(movieDetail.imagePath)
        showMovieName(movieDetail.name)
        showMovieReleaseDate(movieDetail.releaseDate)
    }

    private fun setupUiComponents() {
        setupCastsRecyclerView()
    }

    private fun setupCastsRecyclerView() {
        recycler_view_cast_movie_detail.layoutManager = LinearLayoutManager(this)
        if (!::castMovieDetailAdapter.isInitialized) {
            castMovieDetailAdapter = CastMovieDetailAdapter(this)
        }
        recycler_view_cast_movie_detail.adapter = castMovieDetailAdapter
    }

    override fun onCastItemClick(view: View?, position: Int) {
        movieDetailViewModel.onClickCastItem(castMovieDetailAdapter.getItemByPosition(position), position)
    }

    private fun setupUiEvents() {
        subscribeMovieDetail()
        subscribeNavigatToCastDetail()
        subscribeCastsOfMovie()
        setupAppBarLayoutEvent()
        setupBackButtonEvent()
        movieDetailViewModel.loadMovieDetail()
        movieDetailViewModel.loadCastsOfMovie()
    }

    private fun subscribeCastsOfMovie() {
        movieDetailViewModel.castOfMovie.observe(this, Observer {
            if (it != null) {
                castMovieDetailAdapter.submitList(it)
            }
        })
    }

    private fun subscribeNavigatToCastDetail() {
        movieDetailViewModel.navigateCastDetail.observe(this, Observer {
            it.getContentIfNotHandled()?.let {castTransfer ->
                navigateToCastDetail(castTransfer)
            }
        })
    }


    private fun getCastShareViewPairs(viewPosition: Int): Array<Pair<View, String>> {
        val castLayoutManager = recycler_view_cast_movie_detail.layoutManager
        val itemView = castLayoutManager?.findViewByPosition(viewPosition)
        itemView ?: return arrayOf()
        val textCastName = itemView.findViewById<TextView>(R.id.text_cast_name_horizontal)
        val imageCast = itemView.findViewById<CircleImageView>(R.id.image_cast)
        val textCastPair = Pair<View, String>(textCastName, getString(R.string.text_name_cast_transition))
        val imageCastPair = Pair<View, String>(imageCast, getString(R.string.text_image_cast_transition))

        return arrayOf(textCastPair, imageCastPair)
    }

    private fun navigateToCastDetail(castTransfer: CastTransfer) {
        val viewPosition = movieDetailViewModel.currentPosCastSelected
        val shareViewPairs = getCastShareViewPairs(viewPosition)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *shareViewPairs)
        val castDetailIntent = Intent(this, CastDetailActivity::class.java)
        castDetailIntent.putExtra(CastDetailActivity.BUNDLE_CAST_TRANSFER, castTransfer)
        startActivity(castDetailIntent, options.toBundle())
    }


    private fun setupBackButtonEvent() {
        toolbar_movie_detail.setNavigationOnClickListener {
            supportFinishAfterTransition()
        }
    }


    private fun setupAppBarLayoutEvent() {
        app_bar_movie_detail.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            //refactor later

            val actualHeight = resources.getDimensionPixelSize(R.dimen.height_app_bar) -
                    resources.getDimensionPixelSize(R.dimen.height_toolbar)
            val imageAnchorHeight = resources.getDimensionPixelSize(R.dimen.height_image_movie_vertical)

            if ((verticalOffset * -1) < actualHeight) {
                val distance: Float = actualHeight.toFloat() / imageAnchorHeight
                val textNameY = text_movie_name_detail.y
                val viewOffset = imageAnchorHeight / textNameY
                val translateY = verticalOffset.toFloat() / distance / viewOffset
                text_movie_name_detail.translationY = translateY
                text_movie_categories.translationY = translateY
                text_movie_release_date.translationY = translateY
            }

        })
    }

    private fun subscribeMovieDetail() {
        movieDetailViewModel.movieDetailLiveData.observe(this, Observer {
            showMovieDetailInfo(it)
        })
    }

    private fun showMovieDetailInfo(movieDetail: MovieDetail?) {
        movieDetail?.let {
            showMovieBackground(it.backdropPath)
            showInfoDetail(movieDetail.movieAbout)
        }
    }

    private fun showOverview(overview: String?) {
        text_overview.text = overview
    }


    private fun showMovieAbout(movieAbout: MovieAbout) {
        with(movieAbout) {
            text_original.text = originalTitle
            text_status.text = status
            text_runtime.text = ConvertUtils.convertToHourAndMinute(runtime)
            text_premiere.text = premiere
            text_homepage.text = homePage
            text_budget.text = ConvertUtils.formatMoney(budget!!.toLong())
            text_revenue.text = ConvertUtils.formatMoney(revenue)
            val categories = movieDetailViewModel.getGenreNames(genres = category)
            text_categories.text = categories
            text_movie_categories.text = categories
        }
    }

    private fun showInfoDetail(movieAbout: MovieAbout) {
        with(movieAbout) {
            showOverview(overview)
            showMovieAbout(this)
        }
    }

    private fun showMovieBackground(imageUrl: String?) {
        image_movie_poster.loadImageByUrl("${AppKey.BASE_URL_SLIDER_IMAGE_PATH}$imageUrl")
    }

    private fun showMovieReleaseDate(releaseDate: String) {
        text_movie_release_date.text = releaseDate
    }

    private fun showMovieName(name: String) {
        text_movie_name_detail.text = name
    }

    private fun showImageMovie(imageUrl: String?) {
        imageUrl?.let {
            image_movie_detail.loadImageByUrl("${AppKey.URL_MOVIE_ITEM_IMAGE_PATH}$it")
        }
    }

    override fun getSnackBarViewGroup(): View = root_view

}