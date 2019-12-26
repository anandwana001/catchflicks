package com.akshay.catchflicks.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by akshaynandwana on
 * 26, December, 2019
 **/

data class NetworkError(
    @Expose
    @SerializedName("status_message")
    val statusMessage: String = "The resource you requested could not be found.",

    val success: Boolean = false,

    @Expose
    @SerializedName("status_code")
    val statusCode: Int = 34
)