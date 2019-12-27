package com.akshay.catchflicks.ui.popular

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akshay.catchflicks.R
import com.akshay.catchflicks.di.component.FragmentComponent
import com.akshay.catchflicks.ui.base.BaseFragment
import com.akshay.catchflicks.ui.popular.movie.MovieUpcomingAdapter
import kotlinx.android.synthetic.main.fragment_upcoming.*
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 26, December, 2019
 **/
class UpcomingFragment : BaseFragment<UpcomingViewModel>() {

    companion object {

        const val TAG = "UpcomingFragment"

        fun newInstance(): UpcomingFragment {
            val args = Bundle()
            val fragment = UpcomingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var movieUpcomingAdapter: MovieUpcomingAdapter

    override fun provideLayoutId(): Int = R.layout.fragment_upcoming

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
        rvUpcoming.apply {
            layoutManager = linearLayoutManager
            adapter = movieUpcomingAdapter
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
            pbUpcoming.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.moviesUpcomingList.observe(this, Observer {
            it?.run {
                movieUpcomingAdapter.appendData(this)
            }
        })
    }

}