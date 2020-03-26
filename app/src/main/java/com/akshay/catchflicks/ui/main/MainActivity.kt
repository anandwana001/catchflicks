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

package com.akshay.catchflicks.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.akshay.catchflicks.R
import com.akshay.catchflicks.di.component.ActivityComponent
import com.akshay.catchflicks.ui.base.BaseActivity
import com.akshay.catchflicks.ui.popular.NowPlayingFragment
import com.akshay.catchflicks.ui.popular.PopularFragment
import com.akshay.catchflicks.ui.popular.UpcomingFragment
import com.akshay.catchflicks.ui.search.SearchFragment
import com.akshay.catchflicks.ui.search.SearchViewFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    companion object {
        const val TAG = "MainActivity"
    }

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    private var activeFragment: Fragment? = null

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        bottomNavigation.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.itemPopular -> {
                        viewModel.onPopularSelected()
                        true
                    }
                    R.id.itemNowPlaying -> {
                        viewModel.onNowPlayingSelected()
                        true
                    }
                    R.id.itemUpcoming -> {
                        viewModel.onUpcomingSelected()
                        true
                    }
                    R.id.itemSearch -> {
                        viewModel.onSearchSelected()
                        true
                    }
                    else -> false
                }
            }

        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.popularNavigation.observe(this, Observer {
            it?.run {
                showPopular()
            }
        })

        viewModel.nowPlayingNavigation.observe(this, Observer {
            it?.run {
                showNowPlaying()
            }
        })

        viewModel.upcomingNavigation.observe(this, Observer {
            it?.run {
                showUpcoming()
            }
        })

        viewModel.searchNavigation.observe(this, Observer {
            it?.run {
                showSearch()
            }
        })

        mainSharedViewModel.homeRedirection.observe(this, Observer {
            showSearchView()
        })
    }

    private fun showSearchView() {
        if (activeFragment is SearchViewFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment =
            supportFragmentManager.findFragmentByTag(SearchViewFragment.TAG) as SearchViewFragment?

        if (fragment == null) {
            fragment = SearchViewFragment.newInstance()
            fragmentTransaction.add(R.id.container, fragment, SearchViewFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        checkAndSetUpActiveFragment(fragmentTransaction, fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_appbar_menu, menu)
        return true
    }

    private fun showPopular() {
        if (activeFragment is PopularFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment =
            supportFragmentManager.findFragmentByTag(PopularFragment.TAG) as PopularFragment?

        if (fragment == null) {
            fragment = PopularFragment.newInstance()
            fragmentTransaction.add(R.id.container, fragment, PopularFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        checkAndSetUpActiveFragment(fragmentTransaction, fragment)
    }

    private fun showNowPlaying() {
        if (activeFragment is NowPlayingFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment =
            supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as NowPlayingFragment?

        if (fragment == null) {
            fragment = NowPlayingFragment.newInstance()
            fragmentTransaction.add(R.id.container, fragment, NowPlayingFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        checkAndSetUpActiveFragment(fragmentTransaction, fragment)
    }

    private fun showUpcoming() {
        if (activeFragment is UpcomingFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment =
            supportFragmentManager.findFragmentByTag(UpcomingFragment.TAG) as UpcomingFragment?

        if (fragment == null) {
            fragment = UpcomingFragment.newInstance()
            fragmentTransaction.add(R.id.container, fragment, UpcomingFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        checkAndSetUpActiveFragment(fragmentTransaction, fragment)
    }

    private fun showSearch() {
        if (activeFragment is SearchFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment =
            supportFragmentManager.findFragmentByTag(SearchFragment.TAG) as SearchFragment?

        if (fragment == null) {
            fragment = SearchFragment.newInstance()
            fragmentTransaction.add(R.id.container, fragment, SearchFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        checkAndSetUpActiveFragment(fragmentTransaction, fragment)
    }

    private fun checkAndSetUpActiveFragment(
        fragmentTransaction: FragmentTransaction,
        fragment: Fragment
    ) {
        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)
        fragmentTransaction.commit()
        activeFragment = fragment
    }

}
