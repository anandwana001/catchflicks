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

package com.akshay.catchflicks.ui.search.search

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.ui.base.BaseAdapter
import com.akshay.catchflicks.ui.popular.movie.SearchItemViewHolder

/**
 * Created by akshaynandwana on
 * 02, January, 2020
 **/
class SearchAdapter(
    parentLifecycle: Lifecycle,
    genre: ArrayList<Movie>
) : BaseAdapter<Movie, SearchItemViewHolder>(parentLifecycle, genre) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder =
        SearchItemViewHolder(parent)

}