package com.akshay.catchflicks.ui.popular

import androidx.lifecycle.MutableLiveData
import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.data.repository.NowPlayingRepository
import com.akshay.catchflicks.ui.base.BaseViewModel
import com.akshay.catchflicks.utils.common.Constants
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

/**
 * Created by akshaynandwana on
 * 26, December, 2019
 **/

class NowPlayingViewModel(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper,
    nowPlayingRepository: NowPlayingRepository,
    private val paginator: PublishProcessor<Int>,
    private val allMovieList: ArrayList<Movie>
) : BaseViewModel(compositeDisposable, schedulerProvider, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val moviesNowPlayingList: MutableLiveData<List<Movie>> = MutableLiveData()

    var pageNumber: Int = 1

    init {
        compositeDisposable.addAll(

            paginator.onBackpressureDrop()
                .doOnNext {
                    loading.postValue(true)
                }
                .concatMapSingle { page ->
                    return@concatMapSingle nowPlayingRepository.fetchNowPlayingMovies(
                        language = Constants.LANGUAGE_EN,
                        pageNumber = page
                    )
                        .subscribeOn(schedulerProvider.io())
                        .doOnError {
                            handleNetworkError(it)
                        }
                }
                .subscribe(
                    {
                        allMovieList.addAll(it)

                        loading.postValue(false)
                        moviesNowPlayingList.postValue(it)
                    },
                    {
                        handleNetworkError(it)
                    }
                )
        )
    }

    override fun onCreate() {
        loadMorePosts()
    }

    private fun loadMorePosts() {
        if (checkInternetConnectionWithMessage()) {
            paginator.onNext(pageNumber)
        }
    }

    fun onLoadMore() {
        if (loading.value !== null && loading.value == false) loadMorePosts()
    }

    fun pageUp() {
        pageNumber++
    }

}