package com.akshay.catchflicks.ui.popular.movie

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.akshay.catchflicks.data.model.Genre
import com.akshay.catchflicks.ui.base.BaseAdapter

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
class SearchAdapter(
    parentLifecycle: Lifecycle,
    genre: ArrayList<Genre>
) : BaseAdapter<Genre, SearchItemViewHolder>(parentLifecycle, genre) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder =
        SearchItemViewHolder(parent)

}