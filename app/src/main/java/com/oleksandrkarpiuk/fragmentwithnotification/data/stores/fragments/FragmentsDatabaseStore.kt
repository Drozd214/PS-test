package com.oleksandrkarpiuk.fragmentwithnotification.data.stores.fragments

import com.oleksandrkarpiuk.fragmentwithnotification.database.model.FragmentDatabaseModel

interface FragmentsDatabaseStore {

    suspend fun saveFragment(fragment: FragmentDatabaseModel)
    suspend fun deleteFragment(fragment: FragmentDatabaseModel)
    suspend fun getFragments(): List<FragmentDatabaseModel>

}