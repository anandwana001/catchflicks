package com.akshay.catchflicks.ui.popular

import androidx.lifecycle.MutableLiveData
import com.akshay.catchflicks.ui.base.BaseViewModel
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 26, December, 2019
 **/

class NowPlayingViewModel(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper
) : BaseViewModel(compositeDisposable, schedulerProvider, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {
        loading.postValue(false)
    }

}