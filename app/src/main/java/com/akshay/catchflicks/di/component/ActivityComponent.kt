package com.akshay.catchflicks.di.component

import com.akshay.catchflicks.di.ActivityScope
import com.akshay.catchflicks.di.module.ActivityModule
import com.akshay.catchflicks.ui.main.MainActivity
import com.akshay.catchflicks.ui.popular.detail.PopularDetailActivity
import dagger.Component

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: PopularDetailActivity)

}