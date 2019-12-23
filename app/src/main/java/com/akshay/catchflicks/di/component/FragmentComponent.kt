package com.akshay.catchflicks.di.component

import com.akshay.catchflicks.di.FragmentScope
import com.akshay.catchflicks.di.module.FragmentModule
import dagger.Component

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

}