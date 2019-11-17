package com.uwantolearn.surviverxstream

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.interval(0, 1, TimeUnit.SECONDS)
            .map(Long::toString)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(textView::setText)
            .let(disposable::add)
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}
