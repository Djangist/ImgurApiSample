package pokhilko.aleksandr.ru.data.utils

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
fun <T> Single<T>.schedulersIoToMain(): Single<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.schedulersIoToIo(): Single<T> {
    return subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
}

fun <T> Observable<T>.schedulersIoToMain(): Observable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.schedulersIoToMain(): Flowable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun Completable.schedulersIoToMain(): Completable {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun Completable.schedulersIoToIo(): Completable {
    return subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
}