package com.uwantolearn.surviverxstream

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

    private val proxySubject = PublishSubject.create<String>()

    init {
        Observable.interval(0, 1, TimeUnit.SECONDS)
            .map(Long::toString)
            .subscribe(proxySubject::onNext)
    }

    fun uiState(): Observable<String> = proxySubject.hide()
        .observeOn(AndroidSchedulers.mainThread())

    override fun onCleared() {
        proxySubject.onComplete()
        super.onCleared()
    }
}