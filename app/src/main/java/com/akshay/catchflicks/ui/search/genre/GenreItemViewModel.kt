package com.akshay.catchflicks.ui.popular.movie

import com.akshay.catchflicks.data.model.Genre
import com.akshay.catchflicks.ui.base.BaseItemViewModel
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
class GenreItemViewModel @Inject constructor(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper
) : BaseItemViewModel<Genre>(compositeDisposable, schedulerProvider, networkHelper) {

    companion object {
        const val TAG = "GenreItemViewModel"
    }

    override fun onCreate() {

    }

}