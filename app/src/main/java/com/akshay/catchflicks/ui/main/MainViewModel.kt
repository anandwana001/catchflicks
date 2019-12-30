package com.akshay.catchflicks.ui.main

import androidx.lifecycle.MutableLiveData
import com.akshay.catchflicks.ui.base.BaseViewModel
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 24, December, 2019
 **/

class MainViewModel(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper
) : BaseViewModel(compositeDisposable, schedulerProvider, networkHelper) {

    val popularNavigation = MutableLiveData<Boolean>()
    val nowPlayingNavigation = MutableLiveData<Boolean>()
    val upcomingNavigation = MutableLiveData<Boolean>()
    val searchNavigation = MutableLiveData<Boolean>()

    override fun onCreate() {
        popularNavigation.postValue(true)
    }

    fun onPopularSelected() {
        popularNavigation.postValue(true)
    }

    fun onNowPlayingSelected() {
        nowPlayingNavigation.postValue(true)
    }

    fun onUpcomingSelected() {
        upcomingNavigation.postValue(true)
    }

    fun onSearchSelected() {
        searchNavigation.postValue(true)
    }

}