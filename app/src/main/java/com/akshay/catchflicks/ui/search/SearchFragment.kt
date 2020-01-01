package com.akshay.catchflicks.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.akshay.catchflicks.R
import com.akshay.catchflicks.di.component.FragmentComponent
import com.akshay.catchflicks.ui.base.BaseFragment
import com.akshay.catchflicks.ui.main.MainSharedViewModel
import com.akshay.catchflicks.ui.popular.movie.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 30, December, 2019
 **/

class SearchFragment : BaseFragment<SearchViewModel>() {

    companion object {

        const val TAG = "SearchFragment"

        fun newInstance(): SearchFragment {
            val args = Bundle()
            val fragment = SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    @Inject
    lateinit var searchAdapter: SearchAdapter

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    override fun provideLayoutId(): Int = R.layout.fragment_search

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

        toolbar.setOnClickListener {
            mainSharedViewModel.onHomeRedirect()
        }

        rvGenreList.apply {
            layoutManager = gridLayoutManager
            adapter = searchAdapter
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.genreList.observe(this, Observer {
            it?.run {
                searchAdapter.appendData(this)
            }
        })
    }

}