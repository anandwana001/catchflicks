package com.akshay.catchflicks.ui.popular.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
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
class MovieUpcomingItemViewModel @Inject constructor(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper
) : BaseItemViewModel<Movie>(compositeDisposable, schedulerProvider, networkHelper) {

    companion object {
        const val TAG = "MovieItemViewModel"
    }

    val title: LiveData<String> = Transformations.map(data) {
        it.title
    }

    val overview: LiveData<String> = Transformations.map(data) {
        it.overview
    }

    val posterPath: LiveData<String> = Transformations.map(data) {
        it.poster_path
    }

    val voteAverage: LiveData<Float> = Transformations.map(data) {
        it.vote_average
    }

    override fun onCreate() {

    }

}