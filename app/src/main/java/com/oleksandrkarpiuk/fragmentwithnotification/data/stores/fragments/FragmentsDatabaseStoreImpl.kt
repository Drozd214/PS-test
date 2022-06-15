package com.oleksandrkarpiuk.fragmentwithnotification.data.stores.fragments

import com.oleksandrkarpiuk.fragmentwithnotification.database.daos.FragmentsDao
import com.oleksandrkarpiuk.fragmentwithnotification.database.model.FragmentDatabaseModel

class FragmentsDatabaseStoreImpl(
    private val fragmentsDao: FragmentsDao
) : FragmentsDatabaseStore {

    override suspend fun saveFragment(fragment: FragmentDatabaseModel) {
        fragmentsDao.insertFragment(fragment)
    }

    override suspend fun deleteFragment(fragment: FragmentDatabaseModel) {
        fragmentsDao.deleteFragment(fragment)
    }

    override suspend fun getFragments(): List<FragmentDatabaseModel> {
        return fragmentsDao.getFragments()
    }

}