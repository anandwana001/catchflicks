package com.akshay.catchflicks.data.repository

import com.akshay.catchflicks.data.model.Genre
import com.akshay.catchflicks.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 29, December, 2019
 **/
class GenreRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun fetchGenreList(language: String): Single<List<Genre>> {
        return networkService.doGenreCall(language = language)
            .map {
                it.genres
            }
    }
}