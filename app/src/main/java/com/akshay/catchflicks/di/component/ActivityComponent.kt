package com.akshay.catchflicks.di.component

import com.akshay.catchflicks.di.ActivityScope
import com.akshay.catchflicks.di.module.ActivityModule
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


}