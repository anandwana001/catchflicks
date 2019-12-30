package com.akshay.catchflicks.ui.popular.detail

import androidx.lifecycle.MutableLiveData
import com.akshay.catchflicks.data.model.Genre
import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.data.repository.GenreRepository
import com.akshay.catchflicks.ui.base.BaseViewModel
import com.akshay.catchflicks.utils.common.Constants
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 29, December, 2019
 **/
class PopularDetailViewModel(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper,
    private val genreRepository: GenreRepository
) : BaseViewModel(compositeDisposable, schedulerProvider, networkHelper) {

    val genreList: MutableLiveData<List<Genre>> = MutableLiveData()
    val movieItem: MutableLiveData<Movie> = MutableLiveData()
    val movieItemToShare: MutableLiveData<Movie> = MutableLiveData()

    override fun onCreate() {
        getGenre()
    }

    private fun getGenre() {
        compositeDisposable.addAll(
            genreRepository.fetchGenreList(Constants.LANGUAGE_EN)
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        genreList.postValue(it)
                    },
                    {
                        handleNetworkError(it)
                    }
                )
        )
    }

    fun updateMovieItem(data: Movie) {
        movieItem.postValue(data)
    }

    fun startShareIntent() {
        movieItemToShare.postValue(movieItem.value)
    }

}