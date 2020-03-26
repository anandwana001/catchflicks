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

package com.akshay.catchflicks.di.module

import androidx.lifecycle.ViewModelProviders
import com.akshay.catchflicks.data.repository.GenreRepository
import com.akshay.catchflicks.ui.base.BaseActivity
import com.akshay.catchflicks.ui.detail.DetailViewModel
import com.akshay.catchflicks.ui.main.MainSharedViewModel
import com.akshay.catchflicks.ui.main.MainViewModel
import com.akshay.catchflicks.utils.ViewModelProviderFactory
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(compositeDisposable, schedulerProvider, networkHelper)
        }).get(MainViewModel::class.java)

    @Provides
    fun provideDetailViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        genreRepository: GenreRepository
    ): DetailViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(DetailViewModel::class) {
            DetailViewModel(
                compositeDisposable,
                schedulerProvider,
                networkHelper,
                genreRepository
            )
        }).get(DetailViewModel::class.java)

    @Provides
    fun provideMainSharedViewModel(
        compositeDisposable: CompositeDisposable,
        schedulerProvider: SchedulerProvider,
        networkHelper: NetworkHelper
    ): MainSharedViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainSharedViewModel::class) {
            MainSharedViewModel(compositeDisposable, schedulerProvider, networkHelper)
        }).get(MainSharedViewModel::class.java)
}