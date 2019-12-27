package com.akshay.catchflicks.ui.popular

import androidx.lifecycle.MutableLiveData
import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.data.remote.NetworkService
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

class PopularViewModel(
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper,
    private val networkService: NetworkService,
    private val paginator: PublishProcessor<Pair<String?, String?>>,
    private val allMovieList: ArrayList<Movie>
) : BaseViewModel(compositeDisposable, schedulerProvider, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val moviesList: MutableLiveData<List<Movie>> = MutableLiveData()

    var page: Int = 0

    init {
        compositeDisposable.addAll(

            paginator.onBackpressureDrop()
                .doOnNext {
                    loading.postValue(true)
                }
                .concatMapSingle {
                    return@concatMapSingle networkService.doPopularMoviesCall(
                        language = Constants.LANGUAGE_EN,
                        page = page
                    )
                        .subscribeOn(schedulerProvider.io())
                        .doOnError {
                            handleNetworkError(it)
                        }
                }
                .subscribe(
                    {
                        allMovieList.addAll(it.results)

                        loading.postValue(false)
                        moviesList.postValue(it.results)
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
        val firstPostId = if (allMovieList.isNotEmpty()) allMovieList[0].id else null
        val lastPostId = if (allMovieList.size > 1) allMovieList[allMovieList.size - 1].id else null
        if (checkInternetConnectionWithMessage()) {
            page++
            paginator.onNext(Pair(firstPostId.toString(), lastPostId.toString()))
        }
    }

    fun onLoadMore() {
        if (loading.value !== null && loading.value == false) loadMorePosts()
    }

}