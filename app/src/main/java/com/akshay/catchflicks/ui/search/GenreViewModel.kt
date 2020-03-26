/*
 * MIT License
 *
 * Copyright (c) 2020 Akshay Nandwana
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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