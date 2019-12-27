package com.akshay.catchflicks.ui.popular

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akshay.catchflicks.R
import com.akshay.catchflicks.di.component.FragmentComponent
import com.akshay.catchflicks.ui.base.BaseFragment
import com.akshay.catchflicks.ui.popular.movie.MovieNowPlayingAdapter
import kotlinx.android.synthetic.main.fragment_now_playing.*
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 26, December, 2019
 **/
class NowPlayingFragment : BaseFragment<NowPlayingViewModel>() {

    companion object {

        const val TAG = "NowPlayingFragment"

        fun newInstance(): NowPlayingFragment {
            val args = Bundle()
            val fragment = NowPlayingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var movieAdapter: MovieNowPlayingAdapter

    override fun provideLayoutId(): Int = R.layout.fragment_now_playing

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
        rvNowPlaying.apply {
            layoutManager = linearLayoutManager
            adapter = movieAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    layoutManager?.run {
                        if (this is LinearLayoutManager
                            && itemCount > 0
                            && itemCount == findLastVisibleItemPosition() + 1
                        ) {
                            viewModel.pageUp()
                            viewModel.onLoadMore()
                        }
                    }
                }
            })
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.loading.observe(this, Observer {
            pbNow.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.moviesNowPlayingList.observe(this, Observer {
            it?.run {
                movieAdapter.appendData(this)
            }
        })
    }

}