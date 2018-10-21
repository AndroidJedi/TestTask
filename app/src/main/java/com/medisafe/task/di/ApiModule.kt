package com.medisafe.task.di

import com.medisafe.task.api.Api
import com.medisafe.task.api.adapter.ObserveOnSchedulerCallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val apiModule = module {
    single { createOkHttpClient() }
    single { createWebService<Api>(get()) }
}


const val SERVER_URL = "https://restcountries.eu/"


fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ObserveOnSchedulerCallAdapterFactory.create(AndroidSchedulers.mainThread()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync()).build()
    return retrofit.create(T::class.java)
}
