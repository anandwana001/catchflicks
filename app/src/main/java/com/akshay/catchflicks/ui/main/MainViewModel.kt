package com.akshay.catchflicks.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.akshay.catchflicks.data.model.Genre
import com.akshay.catchflicks.data.remote.NetworkService
import com.akshay.catchflicks.ui.base.BaseViewModel
import com.akshay.catchflicks.utils.common.Constants
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 24, December, 2019
 **/

class MainViewModel(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    private val networkService: NetworkService
) : BaseViewModel(compositeDisposable, schedulerProvider) {

    companion object {
        const val TAG = "MainViewModel"
    }

    val genreList: MutableLiveData<List<Genre>> = MutableLiveData()

    override fun onCreate() {
        getGenreList()
    }

    fun getGenreList() {
        compositeDisposable.addAll(
            networkService.doGenreCall(language = Constants.LANGUAGE_EN)
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        genreList.postValue(it.genres)
                    },
                    {
                        Log.d(TAG, it.toString())
                    }
                )
        )
    }
}