package com.akshay.catchflicks.di.component

import android.app.Application
import android.content.Context
import com.akshay.catchflicks.CatchflicksApplication
import com.akshay.catchflicks.di.ApplicationContext
import com.akshay.catchflicks.di.module.ApplicationModule
import dagger.Component
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

}