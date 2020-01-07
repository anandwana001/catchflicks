package com.akshay.catchflicks.ui.search

import androidx.lifecycle.MutableLiveData
import com.akshay.catchflicks.data.model.Genre
import com.akshay.catchflicks.data.repository.GenreRepository
import com.akshay.catchflicks.ui.base.BaseViewModel
import com.akshay.catchflicks.utils.common.Constants
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 30, December, 2019
 **/
class GenreViewModel(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper,
    private val genreRepository: GenreRepository
) : BaseViewModel(compositeDisposable, schedulerProvider, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val genreList: MutableLiveData<List<Genre>> = MutableLiveData()

    override fun onCreate() {
        getGenre()
    }

    fun getGenre() {

        if (checkInternetConnectionWithMessage()) {
            loading.postValue(true)
            compositeDisposable.addAll(
                genreRepository.fetchGenreList(Constants.LANGUAGE_EN)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            loading.postValue(false)
                            genreList.postValue(it)
                        },
                        {
                            loading.postValue(false)
                            handleNetworkError(it)
                        }
                    )
            )
        }
    }
}