package com.medisafe.task.api.adapter

import io.reactivex.*
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ObserveOnSchedulerCallAdapterFactory(
        private val scheduler: Scheduler
) : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? = when (getRawType(returnType)) {
        Observable::class.java, Completable::class.java, Flowable::class.java, Single::class.java, Maybe::class.java -> {
            retrofit.nextCallAdapter(this, returnType, annotations)?.let { RxJava2ObservableOnSchedulerCallAdapter(it, scheduler) }
        }
        else -> null
    }

    private class RxJava2ObservableOnSchedulerCallAdapter<R>(
            private val callAdapter: CallAdapter<R, *>, private val scheduler: Scheduler
    ) : CallAdapter<R, Any> {

        override fun responseType(): Type = callAdapter.responseType()

        override fun adapt(call: Call<R>): Any = with(callAdapter.adapt(call)) {
            when (this) {
                is Observable<*> -> observeOn(scheduler)
                is Flowable<*> -> observeOn(scheduler)
                is Single<*> -> observeOn(scheduler)
                is Maybe<*> -> observeOn(scheduler)
                is Completable -> observeOn(scheduler)
                else -> this
            }
        }
    }

    companion object {
        @JvmStatic
        fun create(scheduler: Scheduler) = ObserveOnSchedulerCallAdapterFactory(scheduler)
    }
}
