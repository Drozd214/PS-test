package com.oleksandrkarpiuk.fragmentwithnotification.database.daos

import androidx.room.*
import com.oleksandrkarpiuk.fragmentwithnotification.database.model.FragmentDatabaseModel

@Dao
interface FragmentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFragment(fragment: FragmentDatabaseModel)

    @Delete
    fun deleteFragment(fragment: FragmentDatabaseModel)

    @Query("SELECT * FROM ${FragmentDatabaseModel.TABLE_NAME}")
    fun getFragments(): List<FragmentDatabaseModel>
}