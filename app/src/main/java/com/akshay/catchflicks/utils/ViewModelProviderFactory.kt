package com.akshay.catchflicks.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

/** If we want to add argument in the constructor we have to create our own
 * implementation of ViewModelProvider.Factory to create our ViewModel instance.
 *
 * Why we need to create our custom ViewModelProviderFactory?
 *
 * Default -> ViewModelProviders.of(this).get(MyViewModel.class);
 *
 * ViewModelProviders.of, this depends upon a ViewModelStore and a ViewModelFactory
 * **return new ViewModelProvider(ViewModelStores.of(fragment), factory);**
 *
 * ViewModelStore store with a HashMap<String, ViewModel>
 * ViewModelFactory is using reflection to instantiate the ViewModel
 * AndroidViewModelFactory used in ViewModelProviders.of overrides a generic ViewModelFactory
 * ViewModelStoreOwner helps in surviving the configuration changes using Holder class
 *
 * and this whole gives us the viewModel instance without the parameter.
 * So, if we have arguments in our viewModel class constructor then we use custom ViewModelProviderFactory.
 *
 *
 * KClass is the holder of class of type ViewModel that needs to be inject
 * This is the Lambda function, this is provided by the ActivityModule/FragmentModule,
 * when creator lambda is called then that module creates and return the instance of ViewModel
 */


@Singleton
class ViewModelProviderFactory<T : ViewModel>(
    private val kClass: KClass<T>,
    private val creator: () -> T
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(kClass.java)) return creator() as T
        throw IllegalArgumentException("Unknown class name")
    }
}