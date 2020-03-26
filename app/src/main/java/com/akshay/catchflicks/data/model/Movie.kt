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

package com.akshay.catchflicks.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by akshaynandwana on
 * 24, December, 2019
 **/
@Parcelize
data class Movie(

    @Expose
    @SerializedName("popularity")
    val popularity: Float,

    @Expose
    @SerializedName("vote_count")
    val vote_count: Int,

    @Expose
    @SerializedName("video")
    val video: Boolean,

    @Expose
    @SerializedName("poster_path")
    val poster_path: String?,

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("adult")
    val adult: Boolean,

    @Expose
    @SerializedName("backdrop_path")
    val backdrop_path: String?,

    @Expose
    @SerializedName("original_language")
    val original_language: String,

    @Expose
    @SerializedName("original_title")
    val original_title: String,

    @Expose
    @SerializedName("genre_ids")
    val genre_ids: List<Int>,

    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("vote_average")
    val vote_average: Float,

    @Expose
    @SerializedName("overview")
    val overview: String,

    @Expose
    @SerializedName("release_date")
    val release_date: String

) : Parcelable