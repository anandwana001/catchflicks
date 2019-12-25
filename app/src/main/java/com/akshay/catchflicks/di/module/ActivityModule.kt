package com.akshay.catchflicks.di.module

import androidx.lifecycle.ViewModelProviders
import com.akshay.catchflicks.data.remote.NetworkService
import com.akshay.catchflicks.ui.base.BaseActivity
import com.akshay.catchflicks.ui.main.MainViewModel
import com.akshay.catchflicks.utils.ViewModelProviderFactory
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
        networkService: NetworkService
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(compositeDisposable, schedulerProvider, networkService)
        }).get(MainViewModel::class.java)
}