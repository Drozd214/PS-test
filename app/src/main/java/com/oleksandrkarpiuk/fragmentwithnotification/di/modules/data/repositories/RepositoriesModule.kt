package com.oleksandrkarpiuk.fragmentwithnotification.di.modules.data.repositories

import com.oleksandrkarpiuk.fragmentwithnotification.data.repositories.fragments.FragmentsRepository
import com.oleksandrkarpiuk.fragmentwithnotification.data.repositories.fragments.FragmentsRepositoryImpl
import com.oleksandrkarpiuk.fragmentwithnotification.data.stores.fragments.FragmentsDatabaseStore
import com.oleksandrkarpiuk.fragmentwithnotification.mapping.FragmentsMapper
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        StoresModule::class
    ]
)
object RepositoriesModule {

    @Provides
    fun provideFragmentsRepository(
        fragmentsDatabaseStore: FragmentsDatabaseStore,
        fragmentsMapper: FragmentsMapper
    ): FragmentsRepository {
        return FragmentsRepositoryImpl(
            fragmentsDatabaseStore,
            fragmentsMapper
        )
    }

}