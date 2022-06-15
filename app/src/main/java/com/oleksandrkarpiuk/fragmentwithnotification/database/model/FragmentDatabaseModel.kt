package com.oleksandrkarpiuk.fragmentwithnotification.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oleksandrkarpiuk.fragmentwithnotification.database.model.FragmentDatabaseModel.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = TABLE_NAME
)
data class FragmentDatabaseModel(
    @PrimaryKey @ColumnInfo(name = ID) val id: Int,
) : Parcelable {

    companion object {
        const val TABLE_NAME = "fragments"
        const val ID = "id"
    }
}