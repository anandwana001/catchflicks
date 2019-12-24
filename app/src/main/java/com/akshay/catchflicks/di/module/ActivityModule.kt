package com.akshay.catchflicks.di.module

import android.app.Activity
import android.content.Context
import com.akshay.catchflicks.di.ActivityContext
import dagger.Module
import dagger.Provides

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

@Module
class ActivityModule(private val activity: Activity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = activity
}