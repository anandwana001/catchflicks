package com.akshay.catchflicks.data.repository

import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.data.remote.NetworkService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 01, January, 2020
 **/
class SearchRepository @Inject constructor(
    private val networkService: NetworkService
) {
    fun fetchSearchMovies(
        query: String,
        language: String,
        pageNumber: Int
    ): Observable<List<Movie>> {
        return networkService.doSearchMovieCall(
            query = query,
            language = language,
            page = pageNumber
        ).map {
            it.results
        }
    }
}