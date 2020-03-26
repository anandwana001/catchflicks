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

package com.akshay.catchflicks.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshay.catchflicks.data.repository.*
import com.akshay.catchflicks.ui.base.BaseFragment
import com.akshay.catchflicks.ui.main.MainSharedViewModel
import com.akshay.catchflicks.ui.popular.NowPlayingViewModel
import com.akshay.catchflicks.ui.popular.PopularViewModel
import com.akshay.catchflicks.ui.popular.UpcomingViewModel
import com.akshay.catchflicks.ui.popular.movie.GenreAdapter
import com.akshay.catchflicks.ui.popular.movie.MovieAdapter
import com.akshay.catchflicks.ui.popular.movie.MovieNowPlayingAdapter
import com.akshay.catchflicks.ui.popular.movie.MovieUpcomingAdapter
import com.akshay.catchflicks.ui.search.GenreViewModel
import com.akshay.catchflicks.ui.search.SearchViewViewModel
import com.akshay.catchflicks.ui.search.search.SearchAdapter
import com.akshay.catchflicks.utils.ViewModelProviderFactory
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideGridLayoutManager(): GridLayoutManager = GridLayoutManager(fragment.context, 2)

    @Provides
    fun provideMovieAdapter(): MovieAdapter = MovieAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideSearchAdapter(): SearchAdapter = SearchAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideGenreAdapter(): GenreAdapter = GenreAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideMovieNowPlayingAdapter(): MovieNowPlayingAdapter =
        MovieNowPlayingAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideMovieUpcomingAdapter(): MovieUpcomingAdapter =
        MovieUpcomingAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun providePopularViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        popularRepository: PopularRepository
    ): PopularViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(PopularViewModel::class) {
            PopularViewModel(
                compositeDisposable, schedulerProvider, networkHelper, popularRepository,
                PublishProcessor.create(), ArrayList()
            )
        }).get(PopularViewModel::class.java)

    @Provides
    fun provideNowPlayingViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        nowPlayingRepository: NowPlayingRepository
    ): NowPlayingViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(NowPlayingViewModel::class) {
            NowPlayingViewModel(
                compositeDisposable, schedulerProvider, networkHelper, nowPlayingRepository,
                PublishProcessor.create(), ArrayList()
            )
        }).get(NowPlayingViewModel::class.java)

    @Provides
    fun provideUpcomingViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        upcomingRepository: UpcomingRepository
    ): UpcomingViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(UpcomingViewModel::class) {
            UpcomingViewModel(
                compositeDisposable, schedulerProvider, networkHelper, upcomingRepository,
                PublishProcessor.create(), ArrayList()
            )
        }).get(UpcomingViewModel::class.java)

    @Provides
    fun provideGenreViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        genreRepository: GenreRepository
    ): GenreViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(GenreViewModel::class) {
            GenreViewModel(
                compositeDisposable, schedulerProvider, networkHelper, genreRepository
            )
        }).get(GenreViewModel::class.java)

    @Provides
    fun provideSearchViewViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        searchRepository: SearchRepository
    ): SearchViewViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(SearchViewViewModel::class) {
            SearchViewViewModel(
                compositeDisposable, schedulerProvider, networkHelper, searchRepository
            )
        }).get(SearchViewViewModel::class.java)

    @Provides
    fun provideMainSharedViewModel(
        compositeDisposable: CompositeDisposable,
        schedulerProvider: SchedulerProvider,
        networkHelper: NetworkHelper
    ): MainSharedViewModel = ViewModelProviders.of(
        fragment.activity!!, ViewModelProviderFactory(MainSharedViewModel::class) {
            MainSharedViewModel(compositeDisposable, schedulerProvider, networkHelper)
        }).get(MainSharedViewModel::class.java)
}