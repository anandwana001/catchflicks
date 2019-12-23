package com.akshay.catchflicks.di

import javax.inject.Qualifier

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class TempDirectory