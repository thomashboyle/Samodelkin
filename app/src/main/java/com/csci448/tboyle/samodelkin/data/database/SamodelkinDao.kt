package com.csci448.tboyle.samodelkin.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.csci448.tboyle.samodelkin.data.SamodelkinCharacter
import java.util.*

@Dao
interface SamodelkinDao {
    @Insert
    fun addCharacter(char: SamodelkinCharacter)
    @Query("SELECT * FROM character")
    fun getCharacters(): LiveData<List<SamodelkinCharacter>>
    @Query("SELECT * FROM character WHERE id=(:id)")
    fun getCharacter(id: UUID): LiveData<SamodelkinCharacter?>

}