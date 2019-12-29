package com.akshay.catchflicks.ui.popular.movie

import androidx.lifecycle.MutableLiveData
import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.ui.base.BaseItemViewModel
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
class MovieItemViewModel @Inject constructor(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper
) : BaseItemViewModel<Movie>(compositeDisposable, schedulerProvider, networkHelper) {

    companion object {
        const val TAG = "MovieItemViewModel"
    }

    val launchDetail: MutableLiveData<Movie> = MutableLiveData()

    override fun onCreate() {

    }

    fun launchDetailScreen(movie: Movie) {
        launchDetail.postValue(movie)
    }

}