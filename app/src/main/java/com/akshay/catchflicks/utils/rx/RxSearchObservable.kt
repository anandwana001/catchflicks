package com.akshay.catchflicks.utils.rx

import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


/**
 * Created by akshaynandwana on
 * 01, January, 2020
 **/
class RxSearchObservable {

    companion object {
        fun fromView(searchView: SearchView): Observable<String> {

            val subject = PublishSubject.create<String>()

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String): Boolean {
                    subject.onNext(s)
                    subject.onComplete()
                    return true
                }

                override fun onQueryTextChange(text: String): Boolean {
                    subject.onNext(text)
                    return true
                }
            })

            return subject
        }
    }
}