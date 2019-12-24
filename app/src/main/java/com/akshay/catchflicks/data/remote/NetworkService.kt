package com.akshay.catchflicks.data.remote

import com.akshay.catchflicks.data.remote.response.GenreResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

/**
 * Created by akshaynandwana on
 * 24, December, 2019
 **/
@Singleton
interface NetworkService {

    @GET(Endpoints.genre)
    fun doGenreCall(
        @Query("api_key") apiKey: String = Networking.API_KEY,
        @Query("language") language: String?
    ): Single<GenreResponse>
}