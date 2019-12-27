package com.akshay.catchflicks.data.repository

import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
class UpcomingRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun fetchUpcomingMovies(language: String, pageNumber: Int): Single<List<Movie>> {
        return networkService.doUpcomingMovieCall(language = language, page = pageNumber)
            .map {
                it.results
            }
    }
}