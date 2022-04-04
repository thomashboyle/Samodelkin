package com.csci448.tboyle.samodelkin.data

import java.io.Serializable
import java.util.*

data class SamodelkinCharacter(
    val name: String,
    val race: String,
    val dex: String,
    val str: String,
    val wis: String,
    val id: UUID = UUID.randomUUID()) : Serializable
