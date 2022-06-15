package com.oleksandrkarpiuk.fragmentwithnotification.di.modules.core.subcomponents.activities

import com.oleksandrkarpiuk.fragmentwithnotification.data.repositories.fragments.FragmentsRepository
import com.oleksandrkarpiuk.fragmentwithnotification.ui.main.MainActivity
import com.oleksandrkarpiuk.fragmentwithnotification.ui.main.MainViewModelFactory
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [MainComponent.ComponentModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: MainActivity): MainComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideMainViewModelFactory(
            fragmentsRepository: FragmentsRepository
        ): MainViewModelFactory {
            return MainViewModelFactory(
                fragmentsRepository
            )
        }
    }

}