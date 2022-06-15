package com.oleksandrkarpiuk.fragmentwithnotification.di

import android.content.Context
import com.oleksandrkarpiuk.fragmentwithnotification.di.modules.core.CoreModule
import com.oleksandrkarpiuk.fragmentwithnotification.di.modules.core.subcomponents.activities.MainComponent
import com.oleksandrkarpiuk.fragmentwithnotification.di.modules.data.DataModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        DataModule::class
    ]
)
interface MainAppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MainAppComponent
    }

    fun createMainComponent(): MainComponent.Factory

}