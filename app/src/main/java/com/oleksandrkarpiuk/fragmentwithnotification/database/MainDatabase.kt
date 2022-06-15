package com.oleksandrkarpiuk.fragmentwithnotification.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oleksandrkarpiuk.fragmentwithnotification.database.MainDatabase.Companion.VERSION
import com.oleksandrkarpiuk.fragmentwithnotification.database.daos.FragmentsDao
import com.oleksandrkarpiuk.fragmentwithnotification.database.model.FragmentDatabaseModel

@Database(
    entities = [
        FragmentDatabaseModel::class
    ], version = VERSION
)
abstract class MainDatabase : RoomDatabase() {

    companion object {
        const val NAME = "recipe_master_database.db"
        const val VERSION = 1
    }

    abstract val recipeDao: FragmentsDao
}