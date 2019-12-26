package com.akshay.catchflicks.di.module

import androidx.lifecycle.ViewModelProviders
import com.akshay.catchflicks.ui.base.BaseFragment
import com.akshay.catchflicks.ui.popular.NowPlayingViewModel
import com.akshay.catchflicks.ui.popular.PopularViewModel
import com.akshay.catchflicks.ui.popular.UpcomingViewModel
import com.akshay.catchflicks.utils.ViewModelProviderFactory
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun providePopularViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): PopularViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(PopularViewModel::class) {
            PopularViewModel(
                compositeDisposable, schedulerProvider, networkHelper
            )
        }).get(PopularViewModel::class.java)

    @Provides
    fun provideNowPlayingViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): NowPlayingViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(NowPlayingViewModel::class) {
            NowPlayingViewModel(
                compositeDisposable, schedulerProvider, networkHelper
            )
        }).get(NowPlayingViewModel::class.java)

    @Provides
    fun provideUpcomingViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): UpcomingViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(UpcomingViewModel::class) {
            UpcomingViewModel(
                compositeDisposable, schedulerProvider, networkHelper
            )
        }).get(UpcomingViewModel::class.java)
}