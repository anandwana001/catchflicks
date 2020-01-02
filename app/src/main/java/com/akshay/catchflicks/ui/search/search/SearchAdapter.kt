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