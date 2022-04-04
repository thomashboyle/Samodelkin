package com.csci448.tboyle.samodelkin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.csci448.tboyle.samodelkin.data.SamodelkinCharacter
import com.csci448.tboyle.samodelkin.util.CharacterGenerator
import java.util.*

class PreviewSamodelkinCharacterViewModel private constructor(): ISamodelkinCharacterViewModel() {
    private val _characterListLiveData =
            MutableLiveData< MutableList<SamodelkinCharacter> >( mutableListOf())
    private val _characterIdLiveData = MutableLiveData<UUID>()
    override val characterListLiveData: LiveData<List<SamodelkinCharacter>>
            = Transformations.map(_characterListLiveData) { characterList ->
        characterList
    }
    override val characterLiveData = Transformations.map(_characterIdLiveData) { characterID ->
        _characterListLiveData.value?.let { characterList ->
            var char: SamodelkinCharacter? = null
            for (character in characterList) {
                if (character.id == characterID) {
                    char = character
                    break
                }
            }
            char
        }
    }

    companion object {
        private var instance: PreviewSamodelkinCharacterViewModel? = PreviewSamodelkinCharacterViewModel()
        fun getInstance(): PreviewSamodelkinCharacterViewModel {
            var temp = instance
            if (temp == null) {
                temp = PreviewSamodelkinCharacterViewModel()
                instance = temp
            }
            return temp
        }
    }

    override fun addCharacter(char: SamodelkinCharacter) {
        _characterListLiveData.value?.add(char)
//        characterListLiveData = _characterListLiveData.toList()
    }

    override fun loadCharacter(uuid: UUID) {
        _characterIdLiveData.value = uuid
    }

    override fun generateRandomCharacter(): SamodelkinCharacter {
        return CharacterGenerator.generateRandomCharacter()
    }

    init {
        _characterListLiveData.value?.let { characterList ->
            for (i in 1..15) {
                characterList.add(generateRandomCharacter())
            }
            _characterListLiveData.value = characterList
        }
    }

}