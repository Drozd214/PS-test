package com.oleksandrkarpiuk.fragmentwithnotification.mapping

import com.oleksandrkarpiuk.fragmentwithnotification.database.model.FragmentDatabaseModel
import com.oleksandrkarpiuk.fragmentwithnotification.ui.models.FragmentModel

class FragmentsMapperImpl : FragmentsMapper {

    override fun mapFromViewToDatabaseModel(fragment: FragmentModel): FragmentDatabaseModel {
        return FragmentDatabaseModel(
            id = fragment.number
        )
    }

    override fun mapFromDatabaseToViewModel(fragment: FragmentDatabaseModel): FragmentModel {
        return FragmentModel(
            number = fragment.id
        )
    }

}