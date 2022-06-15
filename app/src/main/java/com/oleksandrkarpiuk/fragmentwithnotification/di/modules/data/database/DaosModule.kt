package com.oleksandrkarpiuk.fragmentwithnotification.di.modules.data.database

import com.oleksandrkarpiuk.fragmentwithnotification.database.MainDatabase
import com.oleksandrkarpiuk.fragmentwithnotification.database.daos.FragmentsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DaosModule {

    @Singleton
    @Provides
    fun provideFragmentsDao(roomDatabase: MainDatabase): FragmentsDao {
        return roomDatabase.recipeDao
    }

}