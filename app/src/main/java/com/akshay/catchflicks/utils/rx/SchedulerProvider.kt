package com.akshay.catchflicks.utils.rx

import io.reactivex.Scheduler
import javax.inject.Singleton

/**
 * Created by akshaynandwana on
 * 25, December, 2019
 **/

/* This interface will help in multiple implmentation. Testing too
 */

@Singleton
interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}