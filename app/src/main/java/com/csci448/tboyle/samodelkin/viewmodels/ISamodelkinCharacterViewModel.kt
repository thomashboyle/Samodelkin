package com.csci448.tboyle.samodelkin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.csci448.tboyle.samodelkin.data.SamodelkinCharacter
import java.util.*

abstract class ISamodelkinCharacterViewModel():
    ViewModel() {
    abstract val characterListLiveData: LiveData<List<SamodelkinCharacter>>
    abstract val characterLiveData: LiveData<SamodelkinCharacter?>
    abstract fun addCharacter(char: SamodelkinCharacter)
    abstract fun loadCharacter(uuid: UUID)
    abstract fun generateRandomCharacter(): SamodelkinCharacter
}