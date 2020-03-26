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

package com.akshay.catchflicks.ui.base

import android.view.View
import androidx.annotation.StringRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.akshay.catchflicks.BR
import com.akshay.catchflicks.CatchflicksApplication
import com.akshay.catchflicks.di.component.DaggerViewHolderComponent
import com.akshay.catchflicks.di.component.ViewHolderComponent
import com.akshay.catchflicks.di.module.ViewHolderModule
import com.akshay.catchflicks.utils.display.Toaster
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
abstract class BaseItemViewHolder<T : Any, VM : BaseItemViewModel<T>>(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(
    binding.root
),
    LifecycleOwner {

    @Inject
    lateinit var viewModel: VM

    @Inject
    lateinit var lifecycleRegistry: LifecycleRegistry

    // this is called after the constructor has been called.
    init {
        onCreate()
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    // Call these functions based on recyclerView's lifecycle.

    /**
     * INITIALIZED state called before onCreate
     * RESUMED state called after onCreate
     *
     * onCreate
     * INITIALIZED
     * CREATED
     */
    protected fun onCreate() {
        injectDependencies(buildViewHolderComponent())
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        setupObservers()
        setupView(itemView)
    }

    /**
     * STARTED state called after onStart
     * RESUMED state called after onResume
     *
     * onStarte
     * STARTED
     * onResume
     * RESUMED
     */
    fun onStart() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    /**
     * STARTED state called right before onPause
     * CREATED state called right before onStop
     *
     * pause
     * STARTED
     * stop
     * CREATED
     */
    fun onStop() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    /**
     * DESTROYED state called after onDestroy
     *
     * onDestroy
     * DESTROYED
     */
    fun onDestroy() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    open fun bind(data: T) {
        binding.setVariable(BR.movie, data)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

    private fun buildViewHolderComponent() =
        DaggerViewHolderComponent
            .builder()
            .applicationComponent((itemView.context.applicationContext as CatchflicksApplication).applicationComponent)
            .viewHolderModule(ViewHolderModule(this))
            .build()

    fun showMessage(message: String) = Toaster.show(itemView.context, message)

    fun showMessage(@StringRes resId: Int) = showMessage(itemView.context.getString(resId))

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it?.run { showMessage(this) }
        })
    }

    protected abstract fun injectDependencies(viewHolderComponent: ViewHolderComponent)

    abstract fun setupView(view: View)

}