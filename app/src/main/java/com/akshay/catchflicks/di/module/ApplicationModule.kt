package com.akshay.catchflicks.di.module

import android.app.Application
import android.content.Context
import com.akshay.catchflicks.BuildConfig
import com.akshay.catchflicks.CatchflicksApplication
import com.akshay.catchflicks.data.remote.NetworkService
import com.akshay.catchflicks.data.remote.Networking
import com.akshay.catchflicks.di.ApplicationContext
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.RxSchedulerProvider
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

@Module
class ApplicationModule(private val application: CatchflicksApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.createRetrofitInstance(
            BuildConfig.TMDB_API_KEY,
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024
        )

    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}