package com.example.mymovie2019.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.ErrorState
import com.example.mymovie2019.data.local.model.LoadingState
import com.example.mymovie2019.ui.custom.DialogLoadingFragment
import com.example.mymovie2019.utils.ConnectionLiveData
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {


    private val baseViewModel by lazy {
        createViewModel()
    }

    private val dialogLoading by lazy {
        DialogLoadingFragment.newInstance()
    }

    private val connectionLiveData by lazy {
        ConnectionLiveData(this)
    }


    private lateinit var snackBarMessage: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(application as MyApplication)
        super.onCreate(savedInstanceState)
        dialogLoading
        subscribeLoadingLiveData()
        subscribeErrorMessageLiveData()
        subscribeNetworkState()

    }


    private fun subscribeNetworkState() {
        connectionLiveData.observe(this, Observer { isConnected ->
            when (isConnected) {
                true -> hideErrorMessage()
                else -> showErrorMessageNoAction(getString(R.string.title_network_error), Snackbar.LENGTH_INDEFINITE)
            }
        })
    }


    private fun subscribeErrorMessageLiveData() {
        baseViewModel.errorStateLiveData.observe(this, Observer {
            when (it) {
                is ErrorState.NoAction -> showErrorMessageNoAction(it.message, Toast.LENGTH_SHORT)
                is ErrorState.WithAction -> showErrorMessageWithAction(it)
            }
        })

    }

    private fun showErrorMessageNoAction(message: String, length: Int) {
        snackBarMessage = Snackbar.make(getSnackBarViewGroup(), message, length)
        snackBarMessage.show()
    }


    private fun hideErrorMessage() {
        if (::snackBarMessage.isInitialized && snackBarMessage.isShown) {
            snackBarMessage.dismiss()
        }
    }

    private fun showErrorMessageWithAction(errorMessage: ErrorState.WithAction) {
        Snackbar.make(getSnackBarViewGroup(), errorMessage.message, Snackbar.LENGTH_INDEFINITE)
            .setAction(errorMessage.actionName) {
                errorMessage.action()
            }
            .show()
    }

    private fun subscribeLoadingLiveData() {
        baseViewModel.loadingLiveData.observe(this@BaseActivity, Observer {loadingState ->
            loadingState?.let {
                when (it) {
                    LoadingState.Show -> showLoading()
                    LoadingState.Hide -> hideLoading()
                }
            }
        })
    }

    protected fun showLoading() {
        dialogLoading.show(supportFragmentManager, DialogLoadingFragment.TAG)
    }

    protected fun hideLoading() {
        dialogLoading.dismiss()
    }

    protected fun hideKeyboard(input: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(input.windowToken, 0)
    }

    abstract fun injectDependencies(application: MyApplication)
    abstract fun createViewModel(): BaseViewModel
    abstract fun getSnackBarViewGroup(): View


}