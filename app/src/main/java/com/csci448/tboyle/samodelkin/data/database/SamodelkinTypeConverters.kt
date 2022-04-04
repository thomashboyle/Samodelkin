package com.csci448.tboyle.samodelkin.data.database

import androidx.room.TypeConverter
import java.util.*

class SamodelkinTypeConverters {
    @TypeConverter
    fun fromUUID(id: UUID?) = id?.toString()
    @TypeConverter
    fun toUUID(str: String?) = UUID.fromString(str)
}