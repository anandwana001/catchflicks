/*
 * MIT License
 *
 * Copyright (c) 2020 Akshay Nandwana
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.akshay.catchflicks.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshay.catchflicks.R
import com.akshay.catchflicks.di.component.FragmentComponent
import com.akshay.catchflicks.ui.base.BaseFragment
import com.akshay.catchflicks.ui.search.search.SearchAdapter
import com.akshay.catchflicks.utils.rx.RxSearchObservable
import kotlinx.android.synthetic.main.fragment_search_view.*
import javax.inject.Inject

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

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var searchAdapter: SearchAdapter

    override fun provideLayoutId(): Int = R.layout.fragment_search_view

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
        searchView.setIconifiedByDefault(false)
        searchView.onActionViewExpanded()
        searchView.queryHint = "Search Movie"

        val observable = RxSearchObservable.fromView(searchView)
        viewModel.searchQuery(observable)
        
        rvSearchResult.apply {
            layoutManager = linearLayoutManager
            adapter = searchAdapter
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.searchResult.observe(this, Observer {
            it?.run {
                searchAdapter.appendData(this)
            }
        })

        viewModel.resetSearch.observe(this, Observer {
            if (it) {
                searchAdapter.clearList()
            }
        })
    }

}