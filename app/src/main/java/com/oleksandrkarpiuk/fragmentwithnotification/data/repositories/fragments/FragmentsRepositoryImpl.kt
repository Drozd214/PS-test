package com.oleksandrkarpiuk.fragmentwithnotification.data.repositories.fragments

import com.oleksandrkarpiuk.fragmentwithnotification.data.stores.fragments.FragmentsDatabaseStore
import com.oleksandrkarpiuk.fragmentwithnotification.mapping.FragmentsMapper
import com.oleksandrkarpiuk.fragmentwithnotification.ui.models.FragmentModel

class FragmentsRepositoryImpl(
    private val fragmentsDatabaseStore: FragmentsDatabaseStore,
    private val fragmentsMapper: FragmentsMapper
) : FragmentsRepository {

    override suspend fun saveFragment(fragment: FragmentModel) {
        fragmentsDatabaseStore.saveFragment(fragmentsMapper.mapFromViewToDatabaseModel(fragment))
    }

    override suspend fun deleteFragment(fragment: FragmentModel) {
        fragmentsDatabaseStore.deleteFragment(fragmentsMapper.mapFromViewToDatabaseModel(fragment))
    }

    override suspend fun getFragments(): MutableList<FragmentModel> {
        val fragments = fragmentsDatabaseStore.getFragments()
        return fragments.map{ fragmentsMapper.mapFromDatabaseToViewModel(it) }.toMutableList()
    }

}