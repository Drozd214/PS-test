package com.oleksandrkarpiuk.fragmentwithnotification.mapping

import com.oleksandrkarpiuk.fragmentwithnotification.database.model.FragmentDatabaseModel
import com.oleksandrkarpiuk.fragmentwithnotification.ui.models.FragmentModel

interface FragmentsMapper {

    fun mapFromViewToDatabaseModel(fragment: FragmentModel): FragmentDatabaseModel
    fun mapFromDatabaseToViewModel(fragment: FragmentDatabaseModel): FragmentModel

}