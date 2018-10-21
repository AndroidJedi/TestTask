package com.medisafe.task

import android.app.Application
import com.medisafe.task.di.apiModule
import com.medisafe.task.di.mainModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MedisafeApp : Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin(this, listOf(mainModule, apiModule))
    }
}