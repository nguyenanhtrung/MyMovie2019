package com.example.mymovie2019.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R
import com.example.mymovie2019.ui.base.BaseActivity
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.ui.casts.CastsFragmentDirections
import com.example.mymovie2019.ui.movies.MoviesFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    private val mainViewModel by lazy {
        ViewModelProviders.of(this,viewModelFactory)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.FirstTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUIComponents()
        //
    }

    private fun setupUIComponents() {
        setupToolbar()
        setupBottomNavView()
    }

    private fun setupBottomNavView() {
        bottom_nav_bar.setupWithNavController(findNavController(R.id.fragment_host))
        bottom_nav_bar.setOnNavigationItemReselectedListener {

        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = null
    }

    override fun createViewModel(): BaseViewModel = mainViewModel

    override fun getSnackBarViewGroup(): View = root_layout

    override fun injectDependencies(application: MyApplication) {
        application.appComponent.inject(this)
    }


}
