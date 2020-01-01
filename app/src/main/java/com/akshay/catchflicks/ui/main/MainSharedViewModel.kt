package com.akshay.catchflicks.ui.main

import androidx.lifecycle.MutableLiveData
import com.akshay.catchflicks.ui.base.BaseViewModel
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 01, January, 2020
 **/
class MainSharedViewModel(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper
) : BaseViewModel(compositeDisposable, schedulerProvider, networkHelper) {

    override fun onCreate() {}

    val homeRedirection = MutableLiveData<Boolean>()

    fun onHomeRedirect() {
        homeRedirection.postValue(true)
    }
}