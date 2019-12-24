package com.akshay.catchflicks.data.remote.response

import com.akshay.catchflicks.data.model.Genre
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by akshaynandwana on
 * 24, December, 2019
 **/

/* SerializedName use to map json id with our customized var name.
   If we do not use this, we have to write the var name as json id.
   If we want some var(field) not to parse by gson, we can omit the use of @Expose.
 */

data class GenreResponse(
    @Expose
    @SerializedName("genres")
    val genres: List<Genre>
)