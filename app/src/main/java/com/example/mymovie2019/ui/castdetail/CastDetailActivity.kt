package com.example.mymovie2019.ui.castdetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.navArgs
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R
import com.example.mymovie2019.ui.base.BaseActivity
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.utils.AppKey
import com.example.mymovie2019.utils.loadImageByUrl
import kotlinx.android.synthetic.main.activity_cast_detail.*
import javax.inject.Inject

class CastDetailActivity : BaseActivity() {

    private val castArgs = navArgs<CastDetailActivityArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val castDetailViewModel by lazy {
        ViewModelProviders.of(this@CastDetailActivity, viewModelFactory)[CastDetailViewModel::class.java]
    }

    private lateinit var castDetailPagerAdapter: CastDetailPagerAdapter

    override fun injectDependencies(application: MyApplication) = application.appComponent.inject(this)

    override fun createViewModel(): BaseViewModel = castDetailViewModel

    override fun getSnackBarViewGroup(): View = root_view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cast_detail)
        //
        onReceivedData()
        setupUiComponents()
        setupUiEvents()
    }

    private fun setupUiEvents() {
        setupToolbarEvent()
        subscribeCastCredits()
        castDetailViewModel.loadCastCredits()
    }

    private fun subscribeCastCredits() {
        castDetailViewModel.castCredits.observe(this, Observer {
            if (!::castDetailPagerAdapter.isInitialized) {
                castDetailPagerAdapter = CastDetailPagerAdapter(this@CastDetailActivity,it.first,it.second,supportFragmentManager)
            }
            view_pager_cast_detail.adapter = castDetailPagerAdapter
        })
    }

    private fun setupToolbarEvent() {
        toolbar_cast_detail.setNavigationOnClickListener {
            supportFinishAfterTransition()
        }
    }

    private fun onReceivedData() {
        val castTransfer = castArgs.value.CastTransferDetail
        showCastImage(castTransfer.imageUrl)
        showCastName(castTransfer.name)
        castDetailViewModel.castId = castTransfer.id
    }

    private fun showCastName(name: String) {
        text_cast_name_detail.text = name
    }

    private fun showCastImage(imageUrl: String?) {
        imageUrl?.let {
            image_cast_detail.loadImageByUrl("${AppKey.BASE_URL_IMAGE_PATH}$imageUrl")
        }
    }

    private fun setupUiComponents() {
        setupDetailViewPager()
    }

    private fun setupDetailViewPager() {
        tab_layout_cast_detail.setupWithViewPager(view_pager_cast_detail)
    }


}