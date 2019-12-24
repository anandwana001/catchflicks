package com.akshay.catchflicks.data.remote.response

import com.akshay.catchflicks.data.model.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by akshaynandwana on
 * 24, December, 2019
 **/

data class PopularMoviesResponse(

    @Expose
    @SerializedName("page")
    val page: Int,

    @Expose
    @SerializedName("total_results")
    val totalResults: Int,

    @Expose
    @SerializedName("total_pages")
    val totalPages: Int,

    @Expose
    @SerializedName("results")
    val results: List<Movie>
)