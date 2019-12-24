package com.akshay.catchflicks.data.remote

import com.akshay.catchflicks.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by akshaynandwana on
 * 24, December, 2019
 **/

object Networking {

    private const val NETWORK_CALL_TIMEOUT = 60
    internal lateinit var API_KEY: String

    /* Method to create instance of retrofit and it's central configuration.
       Added addCallAdapterFactory so that we can get the api result in RxJava observable types
       not in retrofit's Call.
       Added addConverterFactory for parsing coming data.
       Added addInterceptor for logging the request and response to logcat.
       This level contains 4 types - NONE,BASIC,HEADERS ANDS BODY.
       We are showing the logs when app is in DEBUG mode.
       We are using Builder here, that is Builder pattern example.
     */

    fun createRetrofitInstance(
        apiKey: String,
        baseUrl: String,
        cacheDir: File,
        cacheSize: Long
    ): NetworkService {
        API_KEY = apiKey
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .cache(Cache(cacheDir, cacheSize))
                    .addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE
                        })
                    .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }
}