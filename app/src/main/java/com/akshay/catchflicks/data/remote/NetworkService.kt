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

package com.akshay.catchflicks.data.remote

import com.akshay.catchflicks.data.remote.response.*
import com.akshay.catchflicks.app.GenreResponse
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