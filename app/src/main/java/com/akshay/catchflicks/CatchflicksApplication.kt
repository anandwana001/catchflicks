package com.akshay.catchflicks

import android.app.Application
import com.akshay.catchflicks.di.component.ApplicationComponent
import com.akshay.catchflicks.di.component.DaggerApplicationComponent
import com.akshay.catchflicks.di.module.ApplicationModule

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/
class CatchflicksApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // for android test
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}