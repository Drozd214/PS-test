package com.oleksandrkarpiuk.fragmentwithnotification.di.modules.data.database

import android.content.Context
import androidx.room.Room
import com.oleksandrkarpiuk.fragmentwithnotification.database.MainDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DaosModule::class
    ]
)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMainDatabase(
        context: Context
    ): MainDatabase {
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            MainDatabase.NAME
        ).apply {
            this.allowMainThreadQueries()
            this.fallbackToDestructiveMigration()
        }.build()
    }

}