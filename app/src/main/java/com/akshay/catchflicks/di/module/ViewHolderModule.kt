package com.akshay.catchflicks.di.module

import androidx.lifecycle.LifecycleRegistry
import com.akshay.catchflicks.di.ViewModelScope
import com.akshay.catchflicks.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)

}