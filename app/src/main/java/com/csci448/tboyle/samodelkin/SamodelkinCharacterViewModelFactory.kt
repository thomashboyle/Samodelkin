package com.csci448.tboyle.samodelkin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.tboyle.samodelkin.data.database.SamodelkinRepository
import com.csci448.tboyle.samodelkin.viewmodels.SamodelkinCharacterViewModel

class SamodelkinCharacterViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    fun getViewModelClass() = SamodelkinCharacterViewModel::class.java

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SamodelkinCharacterViewModel::class.java))
            return modelClass
                .getConstructor(SamodelkinRepository::class.java, Context::class.java)
                .newInstance(SamodelkinRepository.getInstance(context), context)
        throw IllegalArgumentException("Unknown ViewModel")
    }
}