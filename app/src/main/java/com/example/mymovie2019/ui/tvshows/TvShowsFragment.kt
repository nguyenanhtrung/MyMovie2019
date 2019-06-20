package com.example.mymovie2019.ui.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R
import com.example.mymovie2019.ui.base.BaseFragment
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.ui.main.MainViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_tv_shows.*
import javax.inject.Inject

class TvShowsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: TvShowsViewModel
    private lateinit var tvShowAdapter: GroupAdapter<ViewHolder>

    override fun injectDependencies(myApplication: MyApplication) = myApplication.appComponent.inject(this)

    override fun setupBundle() {
    }

    override fun createFragmentViewModel(): BaseViewModel {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[TvShowsViewModel::class.java]
        return viewModel
    }

    override fun bindActivityViewModel(): BaseViewModel = ViewModelProviders.of(requireActivity())[MainViewModel::class.java]

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View = inflater.inflate(R.layout.fragment_tv_shows, container, false)

    override fun setupUIComponents() {
        recycler_view_tv_show.layoutManager = LinearLayoutManager(requireActivity())
        if (!::tvShowAdapter.isInitialized) {
            tvShowAdapter = GroupAdapter()
        }
        recycler_view_tv_show.adapter = tvShowAdapter
    }

    override fun setupUIEvents() {
    }

}