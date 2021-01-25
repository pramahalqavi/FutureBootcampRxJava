package com.example.testapp.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.adapter.DummyAdapter
import com.example.testapp.model.Berry
import com.example.testapp.model.PokeResponse
import com.example.testapp.model.Pokemon
import com.example.testapp.network.DummyApiService
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.functions.Predicate
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxActivity : AppCompatActivity() {

    private val mCompositeDisposable: CompositeDisposable? = CompositeDisposable()

    class Example() {
        var value = -1
        constructor(x: Int) : this() {
            value = x
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx)

//        createOperator()
//        filterOperator()
//        combineOperator()
//        filterOperator()
//        utilityOperator()
//        conditionalOperator()
        callApi()
//        callApiWithFlatMap()
//        callZippedApi()
//        debounceExample()
//        callApi()
    }

    override fun onDestroy() {
        mCompositeDisposable?.clear()
        super.onDestroy()
    }

    private fun debounceExample() {
        val btn = findViewById<Button>(R.id.btn_click)
        val txt = findViewById<TextView>(R.id.txt1)
        var counter = 0
        btn.clicks().throttleFirst(3, TimeUnit.SECONDS).subscribe({
            counter++
            txt.text = counter.toString()
        }, {
            it.printStackTrace()
        })
    }

    private fun createOperator() {
        val createObservable = Observable.create<Int> {
            try {
                for (i in 0..5) {
                    it.onNext(i)
                }
                Log.d("rx create", "$it created thread name ${Thread.currentThread().name}")
                it.onComplete()
            } catch (e: Exception) {
                it.onError(e)
            }
        }
        createObservable.observeOn(Schedulers.io()).subscribe({
            Log.d("rx create onNext", "$it thread name ${Thread.currentThread().name}")
        }, {
            it.printStackTrace()
        })

        val justObservable = Observable.just(1,2,3,4)
        justObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
//            Log.d("rx just onNext", "${it.toString()} thread name ${Thread.currentThread().name}")
        }, {
            it.printStackTrace()
        })

        val fromObservable = Observable.fromArray(1,2,3).repeat(1)
        fromObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
//            Log.d("rx from onNext", "$it thread name ${Thread.currentThread().name}")
        }, {
            it.printStackTrace()
        })

        val intervalObservable = Observable.interval(2, TimeUnit.SECONDS)
        intervalObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.d("rx interval onNext", "$it thread name ${Thread.currentThread().name}")
        }, {
            it.printStackTrace()
        })

//        val timerObservable = Observable.timer(1, TimeUnit.SECONDS)
//        timerObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext {
//            Log.d("rx timer doOnNext", "$it thread name ${Thread.currentThread().name}")
//        }.flatMap {
//            return@flatMap Observable.fromArray(1,2,3)
//        }.subscribe({
//            Log.d("rx timer onNext", "$it thread name ${Thread.currentThread().name}")
//        }, {
//            it.printStackTrace()
//        })

        Observable.interval(2, TimeUnit.SECONDS)

        Observable.just(1,3,5)

        Observable.fromArray(1,3,5)

        Observable.fromIterable(ArrayList<Int>())
    }

    private fun transformOperator() {
//        val flatMapObservable = Observable.just(3,4,5)
//        flatMapObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap {
//            Observable.just(it*2)
//        }.subscribe {
//            Log.d("rx transform", "${it}")
//        }

        val mapObservable = Observable.just(1,2,3)
        mapObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map {
            return@map it*2
        }.subscribe {
            Log.d("rx transform", "${it}")
        }

        val buffer = Observable.just("A", "B", "C", "D", "E", "F")
            .buffer(2).subscribe {

            }
    }

    private fun getNewObservable(): Observable<Int> {
        return Observable.just(1,2,3,4,5)
    }

    private fun getNewObserver(): Observer<Int> {
        return object : Observer<Int> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable?) {

            }

            override fun onNext(t: Int?) {

            }

            override fun onError(e: Throwable?) {

            }

        }
    }

    private fun doSomething() {
//        Log.d("rx doSomething", "Dummy function")
    }

    private fun filterOperator() {
        val distictObservable = Observable.just(10, 20, 20, 10, 30, 40, 70, 60, 70)
            .distinct()

        getNewObservable().filter {
            return@filter it > 3
        }.subscribe {
            doSomething()
        }

        val filterObservable = Observable.just(1,2,3,4,5,6).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).filter {
                return@filter it > 3
            }.subscribe({
             Log.d("rx filter", "${it}")
            }, {
                it.printStackTrace()
            })



    }

    private fun combineOperator() {
        val alphabets1 =
            Observable
                .interval(300, TimeUnit.MILLISECONDS)

        val alphabets2 =
            Observable
                .interval( 450, TimeUnit.MILLISECONDS)

//        Observable.zip(
//            alphabets1,
//            alphabets2,
//            BiFunction { t1: Long, t2: Long -> return@BiFunction Pair(t1, t2) })
//            .subscribe {
//                Log.d("rx combine zip","${it.first}, ${it.second}")
//            }

        Observable.merge(
            alphabets1,
            alphabets2
        ).subscribe {
            Log.d("rx combine merge","${it}")
        }

    }

    private fun utilityOperator() {
        Observable.range(1, 5)
            .subscribeOn(Schedulers.computation())
            .map { i: Int -> i * 100 }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .doOnNext { i: Int ->
                println(
                    "Emitting " + i + " on thread " + Thread.currentThread().name
                )
            }
            .map { i: Int -> i * 10 }
            .doOnNext {  }
            .subscribe { i: Int ->
                println(
                    "Received " + i + " on thread " + Thread.currentThread().name
                )
            }

        val timeoutObservable = Observable.interval(5, TimeUnit.SECONDS).subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).timeout(2, TimeUnit.SECONDS).subscribe({
                Log.d("rx timeout", "success")
            }, {
                Log.d("rx timeout", "error")
            })



//        Observable.just("A", "B", "C", "D", "E", "F")
//            .delay(2, TimeUnit.SECONDS).subscribe {
//                println(it)
//            }
    }

    private fun conditionalOperator() {
        val delay = Observable.just(1).delay(7, TimeUnit.SECONDS)
        Observable.interval(2, TimeUnit.SECONDS).takeWhile {
            return@takeWhile it < 5
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("rx conditional", "${it}")
                }, {
                    it.printStackTrace()
                }
            )

    }

    private fun mathOperator() {
        getNewObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getNewObserver())
    }

    private fun callApi() {
        mCompositeDisposable?.add(DummyApiService.getDummyApi().getPokemons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                items?.let { response ->
                    val pokemons = response.results
                    pokemons?.forEachIndexed { idx, value ->
                        Log.d("rx api call", "${value.name}")
                    }
                    setupAdapter(pokemons as ArrayList<Pokemon>)
                }
            }, {
                it.printStackTrace()
            })
        )
    }

    private fun callApiWithFlatMap() {
        mCompositeDisposable?.add(DummyApiService.getDummyApi().getPokemons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { items ->
                    items?.let { response ->
                        val pokemons = response.results
                        pokemons?.forEachIndexed { idx, value ->
                            Log.d("rx api call", "${value.name}")
                        }
                    }
                return@flatMap DummyApiService.getDummyApi().getBerries()
            }.subscribe( { items ->
                items?.let { response ->
                    val berries = response.results
                    berries?.forEachIndexed { idx, value ->
                        Log.d("rx api call", "${value.name}")
                    }
                }
            }, {
                it.printStackTrace()
            })
        )
    }

    private fun callApiWithMap() {
        mCompositeDisposable?.add(DummyApiService.getDummyApi().getPokemons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { items ->
                items?.let { response ->
                    val pokemons = response.results
                    pokemons?.forEachIndexed { idx, value ->
                        Log.d("rx api call", "${value.name}")
                    }
                    return@map pokemons
                }
            }.subscribe( {
                Log.d("rx api call", "${it?.get(0)?.name}")
            }, {
                it.printStackTrace()
            })
        )
    }

    private fun pokemonApiPolling() {
        val timeout = Observable.timer(20, TimeUnit.SECONDS)
        mCompositeDisposable?.add(Observable.interval(0, 5,  TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).takeUntil(timeout)
            .flatMap {
                return@flatMap DummyApiService.getDummyApi().getPokemons()
            }.takeWhile { response ->
                return@takeWhile response.count ?: 0 > 10
            }.subscribe( { items ->
                items?.let { response ->
                    val pokemons = response.results
                    pokemons?.forEachIndexed { idx, value ->
                        Log.d("rx api call", "${value.name}")
                    }
                }
            }, {
                it.printStackTrace()
            })
        )
    }

    private fun callZippedApi() {
        mCompositeDisposable?.add(
            Observable.zip(DummyApiService.getDummyApi().getPokemons(),
                DummyApiService.getDummyApi().getBerries(),
                BiFunction { t1: PokeResponse<List<Pokemon>>, t2: PokeResponse<List<Berry>> ->
                    return@BiFunction Pair(t1, t2)
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                Log.d("rx zipped", "${it.first.results?.get(0)} ${it.second.results?.get(0)}")
            }, {
                it.printStackTrace()
            })
        )
    }

    private fun setupAdapter(data: ArrayList<Pokemon>) {
        val rv = findViewById<RecyclerView>(R.id.rv_myList)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.addItemDecoration(DividerItemDecoration(rv.context, DividerItemDecoration.VERTICAL))
        rv.adapter = DummyAdapter(this, data)
    }
}