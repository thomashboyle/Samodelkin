package com.csci448.tboyle.samodelkin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.tboyle.samodelkin.viewmodels.SamodelkinCharacterViewModel

class SamodelkinCharacterViewModelFactory: ViewModelProvider.Factory {

    fun getViewModelClass() = SamodelkinCharacterViewModel::class.java

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SamodelkinCharacterViewModel::class.java))
            return SamodelkinCharacterViewModel() as T
        throw IllegalArgumentException("Unknown ViewModel")
    }
}