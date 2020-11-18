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

package com.akshay.catchflicks.ui.detail

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.palette.graphics.Palette
import com.akshay.catchflicks.R
import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.di.component.ActivityComponent
import com.akshay.catchflicks.ui.base.BaseActivity
import com.akshay.catchflicks.utils.common.Constants
import com.akshay.catchflicks.utils.display.ManipulateColor
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.movie_detail.view.*


/**
 * Created by akshaynandwana on
 * 29, December, 2019
 **/
class DetailActivity : BaseActivity<DetailViewModel>() {

    var colorBasedOnImage: Int = 0

    override fun provideLayoutId(): Int = R.layout.activity_detail

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        setUpActionBar()

        viewModel.updateMovieItem(intent.getParcelableExtra<Movie>("movie"))

        fabShare.setOnClickListener { viewModel.startShareIntent() }
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.genreList.observe(this, Observer { genreList ->

            viewModel.movieItem.observe(this, Observer { movieItem ->

                val movieGenreList = movieItem.genre_ids

                val sb = StringBuilder()

                genreList.forEachIndexed { i, genre ->
                    if (movieGenreList.contains((genre.id).toInt())) {
                        sb.append(genre.name).append("   ")
                    }
                }
                movieDetail.tvGenre.text = sb.toString()
            })
        })

        viewModel.movieItem.observe(this, Observer {
            it.let {

                val urlPoster = Constants.IMAGE_URL_PREFIX + it.poster_path
                val urlBackdrop = Constants.IMAGE_URL_PREFIX + it.backdrop_path
                val options = RequestOptions()
                options.centerInside()

                Glide.with(this)
                        .load(urlPoster)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(options)
                        .into(ivPoster)

                Glide.with(this)
                        .asBitmap()
                        .load(urlBackdrop)
                        .listener(object : RequestListener<Bitmap> {
                            override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    isFirstResource: Boolean
                            ): Boolean {
                                startPostponedEnterTransition()
                                return false
                            }

                            override fun onResourceReady(
                                    resource: Bitmap?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                            ): Boolean {
                                startPostponedEnterTransition()
                                resource?.let {
                                    val p = Palette.from(resource).generate()
                                    colorBasedOnImage = p.getMutedColor(
                                            ContextCompat.getColor(
                                                    applicationContext,
                                                    R.color.colorAccent
                                            )
                                    )

                                    collapsingtoolbarlayout.setContentScrimColor(
                                            ManipulateColor.manipulateColor(
                                                    colorBasedOnImage,
                                                    0.62f
                                            )
                                    )

                                    getWindow().setStatusBarColor(
                                            ManipulateColor.manipulateColor(
                                                    colorBasedOnImage,
                                                    0.32f
                                            )
                                    )

                                    movieDetail.tvTitle.setTextColor(
                                            ManipulateColor.manipulateColor(
                                                    colorBasedOnImage,
                                                    0.62f
                                            )
                                    )

                                    movieDetail.tvHeading.setTextColor(
                                            ManipulateColor.manipulateColor(
                                                    colorBasedOnImage,
                                                    0.62f
                                            )
                                    )

                                    movieDetail.tvOverview.setTextColor(
                                            ManipulateColor.manipulateColor(
                                                    colorBasedOnImage,
                                                    0.32f
                                            )
                                    )

                                    movieDetail.tvReleaseDate.setTextColor(
                                            ManipulateColor.manipulateColor(
                                                    colorBasedOnImage,
                                                    0.32f
                                            )
                                    )

                                    movieDetail.tvGenre.setTextColor(
                                            ManipulateColor.manipulateColor(
                                                    colorBasedOnImage,
                                                    0.32f
                                            )
                                    )


                                }
                                return false
                            }
                        }
                        )
                        .apply(options)
                        .into(ivBackdrop)

                movieDetail.tvTitle.text = it.title
                movieDetail.tvReleaseDate.text =
                        resources.getString(R.string.release_date, it.release_date)
                movieDetail.tvOverview.text = it.overview
                movieDetail.tvVoteAverage.text = it.vote_average.toString()
                collapsingtoolbarlayout.title = it.title
                collapsingtoolbarlayout.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
            }
        })

        viewModel.movieItemToShare.observe(this, Observer {

            val urlPoster = Constants.IMAGE_URL_PREFIX + it.poster_path

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, it.title + "\n View Movie Poster here: " + urlPoster)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                goBack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}