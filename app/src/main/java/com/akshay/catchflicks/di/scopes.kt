package com.akshay.catchflicks.di

import javax.inject.Scope

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/


@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class FragmentScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ViewModelScope