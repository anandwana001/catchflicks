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
import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.data.repository.SearchRepository
import com.akshay.catchflicks.ui.base.BaseViewModel
import com.akshay.catchflicks.utils.common.Constants
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import java.util.concurrent.TimeUnit

/**
 * Created by akshaynandwana on
 * 01, January, 2020
 **/
class SearchViewViewModel(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper,
    private val searchRepository: SearchRepository
) : BaseViewModel(compositeDisposable, schedulerProvider, networkHelper) {

    val searchResult: MutableLiveData<List<Movie>> = MutableLiveData()
    val resetSearch: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {

    }

    fun searchQuery(observable: Observable<String>) {
        compositeDisposable.addAll(
            observable
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter { text ->
                    if (!text.isEmpty())
                        resetSearch.postValue(false)
                    else
                        resetSearch.postValue(true)
                    return@filter !text.isEmpty()
                }
                .distinctUntilChanged()
                .switchMap(object : Function<String, ObservableSource<List<Movie>>> {
                    override fun apply(query: String): ObservableSource<List<Movie>> {
                        return searchRepository.fetchSearchMovies(query, Constants.LANGUAGE_EN, 1)
                            .subscribeOn(schedulerProvider.io())
                            .doOnError {
                                handleNetworkError(it)
                            }
                    }
                })
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    {
                        searchResult.postValue(it)
                    }
                )
        )
    }

}
