package com.example.mymovie2019.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R
import com.example.mymovie2019.ui.base.BaseFragment
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.ui.main.MainViewModel
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    companion object {
        const val TAG = "MoviesFragment"

        fun newInstance(): MoviesFragment {
            val fragment = MoviesFragment()
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val moviesViewModel by lazy {
        ViewModelProviders.of(this,viewModelFactory)[MoviesViewModel::class.java]
    }

    private val activityViewModel by lazy {
        ViewModelProviders.of(requireActivity())[MainViewModel::class.java]
    }

    override fun injectDependencies(myApplication: MyApplication) {
        val appComponent = myApplication.appComponent
        appComponent.inject(this)
    }

    override fun createFragmentViewModel(): BaseViewModel = moviesViewModel
    override fun bindActivityViewModel(): BaseViewModel = activityViewModel

    override fun setupBundle() {

    }

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_movies,container,false)
    }

    override fun setupUIComponents() {

    }

    override fun setupUIEvents() {
    }
}