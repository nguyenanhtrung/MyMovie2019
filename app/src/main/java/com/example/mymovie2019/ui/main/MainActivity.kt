package com.example.mymovie2019.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R
import com.example.mymovie2019.ui.base.BaseActivity
import com.example.mymovie2019.ui.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    private val mainViewModel by lazy {
        ViewModelProviders.of(this,viewModelFactory)[MainViewModel::class.java]
    }

    private val appBarConfiguration by lazy {
        AppBarConfiguration(findNavController(R.id.fragment_host).graph)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUIComponents()
        //test
        mainViewModel.loadGenresFromServer()
    }

    private fun setupUIComponents() {
        setupToolbar()
        setupBottomNavView()
    }

    private fun setupBottomNavView() {
        bottom_nav_bar.setupWithNavController(findNavController(R.id.fragment_host))
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(findNavController(R.id.fragment_host),appBarConfiguration)
    }

    override fun createViewModel(): BaseViewModel = mainViewModel

    override fun getSnackBarViewGroup(): View = root_layout

    override fun injectDependencies(myApplication: MyApplication) {
        myApplication.appComponent.inject(this)
    }
}
