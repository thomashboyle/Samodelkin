package com.csci448.tboyle.samodelkin.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "character")
data class SamodelkinCharacter(
    val name: String,
    val race: String,
    val dex: String,
    val str: String,
    val wis: String,
    @PrimaryKey
    val id: UUID = UUID.randomUUID()) : Serializable
