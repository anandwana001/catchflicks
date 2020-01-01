package com.akshay.catchflicks.di.module

import androidx.lifecycle.ViewModelProviders
import com.akshay.catchflicks.data.repository.GenreRepository
import com.akshay.catchflicks.ui.base.BaseActivity
import com.akshay.catchflicks.ui.main.MainSharedViewModel
import com.akshay.catchflicks.ui.main.MainViewModel
import com.akshay.catchflicks.ui.popular.detail.PopularDetailViewModel
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
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(compositeDisposable, schedulerProvider, networkHelper)
        }).get(MainViewModel::class.java)

    @Provides
    fun provideDetailViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        genreRepository: GenreRepository
    ): PopularDetailViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(PopularDetailViewModel::class) {
            PopularDetailViewModel(
                compositeDisposable,
                schedulerProvider,
                networkHelper,
                genreRepository
            )
        }).get(PopularDetailViewModel::class.java)

    @Provides
    fun provideMainSharedViewModel(
        compositeDisposable: CompositeDisposable,
        schedulerProvider: SchedulerProvider,
        networkHelper: NetworkHelper
    ): MainSharedViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainSharedViewModel::class) {
            MainSharedViewModel(compositeDisposable, schedulerProvider, networkHelper)
        }).get(MainSharedViewModel::class.java)
}