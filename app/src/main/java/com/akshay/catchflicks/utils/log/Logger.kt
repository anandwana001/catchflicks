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

package com.akshay.catchflicks.utils.log

import com.akshay.catchflicks.BuildConfig
import timber.log.Timber

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

object Logger {

    init {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    fun d(tag: String, s: String, vararg params: Any) =
        Timber.tag(tag).d(s, params)

    fun d(tag: String, throwable: Throwable, s: String, vararg params: Any) =
        Timber.tag(tag).d(throwable, s, params)

    fun i(tag: String, s: String, vararg params: Any) =
        Timber.tag(tag).i(s, params)

    fun i(tag: String, throwable: Throwable, s: String, vararg params: Any) =
        Timber.tag(tag).i(throwable, s, params)

    fun w(tag: String, s: String, vararg params: Any) =
        Timber.tag(tag).w(s, params)

    fun w(tag: String, throwable: Throwable, s: String, vararg params: Any) =
        Timber.tag(tag).w(throwable, s, params)

    fun e(tag: String, s: String, vararg params: Any) =
        Timber.tag(tag).e(s, params)

    fun e(tag: String, throwable: Throwable, s: String, vararg params: Any) =
        Timber.tag(tag).e(throwable, s, params)

}
