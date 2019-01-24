package com.example.mymovie2019.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mymovie2019.data.local.model.LoadingState

abstract class BaseFragment : Fragment() {

    private val baseViewModel by lazy {
        createFragmentViewModel()
    }

    private val activityViewModel by lazy {
        bindActivityViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        baseViewModel
        activityViewModel
        setupBundle()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflateLayout(inflater,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLoadingState()
        subscribeErrorState()
        setupUIComponents()
        setupUIEvents()
    }

    private fun subscribeErrorState() {
        baseViewModel.errorStateLiveData.observe(viewLifecycleOwner, Observer {
            activityViewModel.showError(it)
        })
    }

    private fun subscribeLoadingState() {
        baseViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is LoadingState.Show -> activityViewModel.showLoading()
                is LoadingState.Hide -> activityViewModel.hideLoading()
            }
        })
    }

    abstract fun injectDependencies()
    abstract fun setupBundle()
    abstract fun createFragmentViewModel() : BaseViewModel
    abstract fun bindActivityViewModel() : BaseViewModel
    abstract fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?) : View
    abstract fun setupUIComponents()
    abstract fun setupUIEvents()
}