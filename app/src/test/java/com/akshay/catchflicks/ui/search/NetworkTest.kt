package com.akshay.catchflicks.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.akshay.catchflicks.R
import com.akshay.catchflicks.data.repository.GenreRepository
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.TestSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by akshaynandwana on
 * 07, January, 2020
 **/
@RunWith(MockitoJUnitRunner::class)
class NetworkTest {

    /**
     * Without this, error -> java.lang.RuntimeException: Method getMainLooper in android.os.Looper not mocked.
     * Swaps the background executor used by the Architecture Components
     * with a different one which executes each task synchronously
     */
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    private lateinit var genreViewModel: GenreViewModel

    @Mock
    private lateinit var genreRepository: GenreRepository

    @Mock
    private lateinit var messageStringIdObserver: Observer<Int>

    private lateinit var testScheduler: TestScheduler

    /**
     * TestScheduler, manual timing
     * observeForever -> this is always observe and never stops observing automatically
     */
    @Before
    fun setup() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        genreViewModel = GenreViewModel(
            compositeDisposable,
            testSchedulerProvider,
            networkHelper,
            genreRepository
        )
        genreViewModel.messageStringId.observeForever(messageStringIdObserver)
    }

    /**
     * We want to check that if there is no internet will it show the error or not.
     * We are using doReturn to bring false from isNetworkConnected method, this will tell
     * our piece of code forcefully that there is no internet.
     * genreViewModel.getGenre() -> calling the method which calls api and check if internet is there or not
     * We are asserting the value to check if the value in the LiveData is same as what we are thinking of.
     * We are verifying that our observer also have the value when get changed.
     */
    @Test
    fun givenNoInternet_whenGenreCall_shouldShowError() {
        doReturn(false)
            .`when`(networkHelper)
            .isNetworkConnected()
        genreViewModel.getGenre()
        assert(genreViewModel.messageStringId.value == R.string.network_connection_error)
        verify(messageStringIdObserver).onChanged(R.string.network_connection_error)
    }

    /**
     * removing the observer manually because we observeForever it
     */
    @After
    fun tearDown() {
        genreViewModel.messageStringId.removeObserver(messageStringIdObserver)
    }
}