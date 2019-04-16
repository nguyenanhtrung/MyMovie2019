package com.example.mymovie2019.ui.casts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.CastTransfer
import com.example.mymovie2019.ui.base.BaseFragment
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.ui.main.MainViewModel
import com.example.mymovie2019.utils.EndlessScrollListener
import com.example.mymovie2019.utils.MyGridDividerItemDecoration
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_casts.*
import javax.inject.Inject

class CastsFragment : BaseFragment(), CastGridAdapter.OnClickCastItemListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var castsViewModel: CastsViewModel

    private lateinit var castGridAdapter: CastGridAdapter

    override fun injectDependencies(myApplication: MyApplication) {
        myApplication.appComponent.inject(this@CastsFragment)
    }

    override fun setupBundle() {
    }

    override fun createFragmentViewModel(): BaseViewModel {
        castsViewModel = ViewModelProviders.of(this@CastsFragment, viewModelFactory)[CastsViewModel::class.java]
        return castsViewModel
    }

    override fun bindActivityViewModel(): BaseViewModel {
        return ViewModelProviders.of(requireActivity())[MainViewModel::class.java]
    }

    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_casts, container, false)
    }

    override fun setupUIComponents() {
        setupCastsRecyclerView()
    }

    private fun setupCastsRecyclerView() {
        recycler_view_cast_grid.apply {
            layoutManager = GridLayoutManager(requireActivity(), 3)
            val gridItemDecoration = MyGridDividerItemDecoration(16, 3)
            addItemDecoration(gridItemDecoration)
        }
        if (!::castGridAdapter.isInitialized) {
            castGridAdapter = CastGridAdapter(this@CastsFragment)
        }

        recycler_view_cast_grid.adapter = castGridAdapter

    }

    override fun setupUIEvents() {
        subscribeListCast()
        subscribeNavigateToCastDetail()
        setCastRecyclerViewOnScrolledListener()
        castsViewModel.loadPopularCasts()
    }

    private fun subscribeNavigateToCastDetail() {
        castsViewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { castTransfer ->
                val castLayoutManager = recycler_view_cast_grid.layoutManager
                val curCastItemView = castLayoutManager?.findViewByPosition(castsViewModel.currentPosCastSelected)
                curCastItemView?.let { itemView ->
                    val textCastName = itemView.findViewById<TextView>(R.id.text_cast_name)
                    val imageCast = itemView.findViewById<CircleImageView>(R.id.image_item_cast_grid)
                    navigateToCastDetail(castTransfer, imageCast, textCastName)
                }
            }
        })
    }

    private fun navigateToCastDetail(castTransfer: CastTransfer, imageCast:CircleImageView, textCastName: TextView) {
        val imagePair = Pair<View, String>(imageCast, getString(R.string.text_image_cast_transition))
        val namePair = Pair<View,String>(textCastName, getString(R.string.text_name_cast_transition))

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), imagePair, namePair)
        val extras = ActivityNavigator.Extras.Builder().setActivityOptions(options).build()
        val navToDetailAction = CastsFragmentDirections.actionCastsDestToCastDetailDest(castTransfer)
        findNavController().navigate(navToDetailAction, extras)
    }

    private fun setCastRecyclerViewOnScrolledListener() {
        recycler_view_cast_grid.addOnScrollListener(object : EndlessScrollListener(recycler_view_cast_grid.layoutManager as GridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                castsViewModel.onLoadMorePopularCast(page)
            }
        })
    }

    private fun subscribeListCast() {
        castsViewModel.castsLiveData.observe(viewLifecycleOwner, Observer {
            castGridAdapter.submitList(it)
        })
    }

    override fun onCastItemClick(view: View?, position: Int) {
        castsViewModel.onCastItemClick(position)
    }

}