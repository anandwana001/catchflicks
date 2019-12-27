package com.akshay.catchflicks.ui.base

import androidx.lifecycle.MutableLiveData
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
abstract class BaseItemViewModel<T : Any>(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper
) : BaseViewModel(compositeDisposable, schedulerProvider, networkHelper) {

    val data: MutableLiveData<T> = MutableLiveData()

    /**
     * We are using BaseViewModel with this custom viewHolder. onCleared() is manage by system for activity or fragment,
     * not by viewHolder. So, we have to call it manually.
     */
    fun onManualCleared() = onCleared()

    fun updateData(data: T) {
        this.data.postValue(data)
    }
}