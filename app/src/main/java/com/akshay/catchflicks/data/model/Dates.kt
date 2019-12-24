package com.akshay.catchflicks.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by akshaynandwana on
 * 24, December, 2019
 **/
data class Dates(
    @Expose
    @SerializedName("maximum")
    val maximum: String,

    @Expose
    @SerializedName("minimum")
    val minimum: String
)