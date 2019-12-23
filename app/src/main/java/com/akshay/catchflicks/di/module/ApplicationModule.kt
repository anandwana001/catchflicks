package com.akshay.catchflicks.di.module

import android.app.Application
import android.content.Context
import com.akshay.catchflicks.CatchflicksApplication
import com.akshay.catchflicks.di.ApplicationContext
import dagger.Module
import dagger.Provides
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
}