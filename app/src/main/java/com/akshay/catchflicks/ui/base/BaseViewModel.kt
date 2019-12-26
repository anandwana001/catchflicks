package com.akshay.catchflicks.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akshay.catchflicks.R
import com.akshay.catchflicks.utils.network.NetworkHelper
import com.akshay.catchflicks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.net.ssl.HttpsURLConnection

/**
 * Created by akshaynandwana on
 * 25, December, 2019
 **/

/**
 * compositeDisposable contains all the observables and activity is finished.
 * Now, as there is no activity and request finish and brings data to view which is not there.
 * So, we have to call dispose() to tell observables not to provide the data to subscribe.
 **/

abstract class BaseViewModel(
    protected val compositeDisposable: CompositeDisposable,
    protected val schedulerProvider: SchedulerProvider,
    protected val networkHelper: NetworkHelper
) : ViewModel() {

    val messageStringId: MutableLiveData<Int> = MutableLiveData()
    val messageString: MutableLiveData<String> = MutableLiveData()

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun checkInternetConnectionWithMessage() =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(R.string.network_connection_error)
            false
        }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    protected fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                when (statusCode) {
                    -1 -> {
                        messageStringId.postValue(R.string.network_default_error)
                    }
                    0 -> {
                        messageStringId.postValue(R.string.server_connection_error)
                    }
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        messageStringId.postValue(R.string.permission_denied)
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR -> {
                        messageStringId.postValue(R.string.network_internal_error)
                    }
                    HttpsURLConnection.HTTP_UNAVAILABLE -> {
                        messageStringId.postValue(R.string.network_server_not_available)
                    }
                    else -> {
                        messageString.postValue(statusMessage)
                    }
                }
            }
        }

    abstract fun onCreate()
}