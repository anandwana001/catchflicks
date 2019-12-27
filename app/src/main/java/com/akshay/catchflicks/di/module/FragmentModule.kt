package com.akshay.catchflicks.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshay.catchflicks.data.repository.NowPlayingRepository
import com.akshay.catchflicks.data.repository.PopularRepository
import com.akshay.catchflicks.data.repository.UpcomingRepository
import com.akshay.catchflicks.ui.base.BaseFragment
import com.akshay.catchflicks.ui.popular.NowPlayingViewModel
import com.akshay.catchflicks.ui.popular.PopularViewModel
import com.akshay.catchflicks.ui.popular.UpcomingViewModel
import com.akshay.catchflicks.ui.popular.movie.MovieAdapter
import com.akshay.catchflicks.ui.popular.movie.MovieNowPlayingAdapter
import com.akshay.catchflicks.ui.popular.movie.MovieUpcomingAdapter
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
    fun provideMovieAdapter(): MovieAdapter = MovieAdapter(fragment.lifecycle, ArrayList())

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
}