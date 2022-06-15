package com.oleksandrkarpiuk.fragmentwithnotification.di.modules.core

import com.oleksandrkarpiuk.fragmentwithnotification.di.modules.core.subcomponents.activities.ActivitiesModule
import com.oleksandrkarpiuk.fragmentwithnotification.mapping.FragmentsMapper
import com.oleksandrkarpiuk.fragmentwithnotification.mapping.FragmentsMapperImpl
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ActivitiesModule::class
    ]
)
object CoreModule {

    @Provides
    fun provideFragmentsMapper(): FragmentsMapper {
        return FragmentsMapperImpl()
    }

}