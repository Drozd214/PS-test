package com.oleksandrkarpiuk.fragmentwithnotification.di.modules.data

import com.oleksandrkarpiuk.fragmentwithnotification.di.modules.data.database.DatabaseModule
import com.oleksandrkarpiuk.fragmentwithnotification.di.modules.data.repositories.RepositoriesModule
import dagger.Module

@Module(
    includes = [
        DatabaseModule::class,
        RepositoriesModule::class
    ]
)
object DataModule