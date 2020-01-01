package com.akshay.catchflicks.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.akshay.catchflicks.R
import com.akshay.catchflicks.di.component.FragmentComponent
import com.akshay.catchflicks.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_search_view.*

/**
 * Created by akshaynandwana on
 * 01, January, 2020
 **/
class SearchViewFragment : BaseFragment<SearchViewViewModel>() {

    companion object {

        const val TAG = "SearchViewFragment"

        fun newInstance(): SearchViewFragment {
            val args = Bundle()
            val fragment = SearchViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_search_view

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
        searchView.setIconifiedByDefault(false)
        searchView.onActionViewExpanded()
        searchView.queryHint = "Search Movie"
        viewModel.searchQuery(searchView)
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.searchResult.observe(this, Observer {
            tvSearchResult.text = it
        })
    }

}