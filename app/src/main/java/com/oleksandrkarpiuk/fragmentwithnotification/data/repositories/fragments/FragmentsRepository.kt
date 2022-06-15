package com.oleksandrkarpiuk.fragmentwithnotification.data.repositories.fragments

import com.oleksandrkarpiuk.fragmentwithnotification.ui.models.FragmentModel

interface FragmentsRepository {

    suspend fun saveFragment(fragment: FragmentModel)
    suspend fun deleteFragment(fragment: FragmentModel)
    suspend fun getFragments(): MutableList<FragmentModel>

}