package com.akshay.catchflicks.ui.base

import androidx.lifecycle.ViewModel
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 25, December, 2019
 **/

/**
 * compositeDisposable contains all the observables and activity is finished.
 * Now, as there is no activity and request finish and brings data to view which is not there.
 * So, we have to call dispose() to tell observables not to provide the data to subscribe.
 **/

abstract class BaseViewModel(
    protected val compositeDisposable: CompositeDisposable,
    protected val schedulerProvider: SchedulerProvider
) : ViewModel() {

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    abstract fun onCreate()
}