package com.akshay.catchflicks.data.repository

import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
class PopularRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun fetchPopularMovies(language: String, pageNumber: Int): Single<List<Movie>> {
        return networkService.doPopularMoviesCall(language = language, page = pageNumber)
            .map {
                it.results
            }
    }
}