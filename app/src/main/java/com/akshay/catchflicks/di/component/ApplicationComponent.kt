package com.akshay.catchflicks.di.component

import android.app.Application
import android.content.Context
import com.akshay.catchflicks.CatchflicksApplication
import com.akshay.catchflicks.data.remote.NetworkService
import com.akshay.catchflicks.di.ApplicationContext
import com.akshay.catchflicks.di.module.ApplicationModule
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: CatchflicksApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getCompositeDisposable(): CompositeDisposable

    fun getSchedulerProvider(): SchedulerProvider

    fun getNetworkService(): NetworkService

    fun getNetworkHelper(): NetworkHelper
}