package com.akshay.catchflicks.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.akshay.catchflicks.data.model.Genre
import com.akshay.catchflicks.data.remote.NetworkService
import com.akshay.catchflicks.di.ActivityScope
import com.akshay.catchflicks.utils.common.Constants
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 24, December, 2019
 **/
@ActivityScope
class MainViewModel @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService
) {

    companion object {
        const val TAG = "MainViewModel"
    }

    val genreList: MutableLiveData<List<Genre>> = MutableLiveData()

    fun getGenreList() {
        compositeDisposable.addAll(
            networkService.doGenreCall(language = Constants.LANGUAGE_EN)
                .subscribeOn(Schedulers.io())
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

    fun onDestroy() {
        compositeDisposable.dispose()
    }
}