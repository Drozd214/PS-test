package com.oleksandrkarpiuk.fragmentwithnotification.di.modules.data.repositories

import com.oleksandrkarpiuk.fragmentwithnotification.data.stores.fragments.FragmentsDatabaseStore
import com.oleksandrkarpiuk.fragmentwithnotification.data.stores.fragments.FragmentsDatabaseStoreImpl
import com.oleksandrkarpiuk.fragmentwithnotification.database.daos.FragmentsDao
import dagger.Module
import dagger.Provides

@Module
object StoresModule {

    @Provides
    fun provideFragmentsDatabaseStore(
        fragmentsDao: FragmentsDao
    ): FragmentsDatabaseStore {
        return FragmentsDatabaseStoreImpl(fragmentsDao)
    }

}