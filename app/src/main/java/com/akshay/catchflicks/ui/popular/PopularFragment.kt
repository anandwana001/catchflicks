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

package com.akshay.catchflicks.ui.popular

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akshay.catchflicks.R
import com.akshay.catchflicks.di.component.FragmentComponent
import com.akshay.catchflicks.ui.base.BaseFragment
import com.akshay.catchflicks.ui.popular.movie.MovieAdapter
import kotlinx.android.synthetic.main.fragment_popular.*
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 26, December, 2019
 **/
class PopularFragment : BaseFragment<PopularViewModel>() {

    companion object {

        const val TAG = "PopularFragment"

        fun newInstance(): PopularFragment {
            val args = Bundle()
            val fragment = PopularFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun provideLayoutId(): Int = R.layout.fragment_popular

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
        rvPopularMovies.apply {
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
            pbPop.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.moviesList.observe(this, Observer {
            it?.run {
                movieAdapter.appendData(this)
            }
        })
    }

}