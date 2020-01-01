package com.akshay.catchflicks.di.component

import com.akshay.catchflicks.di.FragmentScope
import com.akshay.catchflicks.di.module.FragmentModule
import com.akshay.catchflicks.ui.popular.NowPlayingFragment
import com.akshay.catchflicks.ui.popular.PopularFragment
import com.akshay.catchflicks.ui.popular.UpcomingFragment
import com.akshay.catchflicks.ui.search.SearchFragment
import com.akshay.catchflicks.ui.search.SearchViewFragment
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

    fun inject(fragment: PopularFragment)

    fun inject(fragment: NowPlayingFragment)

    fun inject(fragment: UpcomingFragment)

    fun inject(fragment: SearchFragment)

    fun inject(fragment: SearchViewFragment)
}