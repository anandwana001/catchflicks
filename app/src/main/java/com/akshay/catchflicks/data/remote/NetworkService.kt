package com.akshay.catchflicks.data.remote

import com.akshay.catchflicks.data.remote.response.*
import io.reactivex.Observable
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

    @GET(Endpoints.GENRE)
    fun doGenreCall(
        @Query("api_key") apiKey: String = Networking.API_KEY,
        @Query("language") language: String?
    ): Single<GenreResponse>

    @GET(Endpoints.MOVIES_POPULAR)
    fun doPopularMoviesCall(
        @Query("api_key") apiKey: String = Networking.API_KEY,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Single<PopularMoviesResponse>

    @GET(Endpoints.MOVIES_NOW_PLAYING)
    fun doNowPlayingMovieCall(
        @Query("api_key") apiKey: String = Networking.API_KEY,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Single<NowPlayingMoviesResponse>

    @GET(Endpoints.MOVIES_UPCOMING)
    fun doUpcomingMovieCall(
        @Query("api_key") apiKey: String = Networking.API_KEY,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Single<UpcomingMoviesResponse>

    @GET(Endpoints.MOVIES_SEARCH)
    fun doSearchMovieCall(
        @Query("api_key") apiKey: String = Networking.API_KEY,
        @Query("language") language: String?,
        @Query("query") query: String,
        @Query("page") page: Int?
    ): Observable<SearchMoviesResponse>


}