package com.akshay.catchflicks.di.component

import com.akshay.catchflicks.di.ViewModelScope
import com.akshay.catchflicks.di.module.ViewHolderModule
import com.akshay.catchflicks.ui.popular.movie.*
import dagger.Component

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: MovieItemViewHolder)

    fun inject(viewHolder: MovieNowPlayingItemViewHolder)

    fun inject(viewHolder: MovieUpcomingItemViewHolder)

    fun inject(viewHolder: SearchItemViewHolder)

    fun inject(viewHolder: GenreItemViewHolder)

}