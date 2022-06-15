package com.oleksandrkarpiuk.fragmentwithnotification

import android.app.Application
import com.oleksandrkarpiuk.fragmentwithnotification.di.DaggerMainAppComponent
import com.oleksandrkarpiuk.fragmentwithnotification.di.MainAppComponent

class MainApplication : Application() {

    private lateinit var appComponent: MainAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerMainAppComponent.factory().create(applicationContext)
    }

    fun getComponent(): MainAppComponent = appComponent

}