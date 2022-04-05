package com.csci448.tboyle.samodelkin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.csci448.tboyle.samodelkin.data.SamodelkinCharacter
import com.csci448.tboyle.samodelkin.data.database.SamodelkinRepository
import com.csci448.tboyle.samodelkin.util.CharacterGenerator
import java.util.*

class SamodelkinCharacterViewModel (private val samodelkinRepository: SamodelkinRepository): ISamodelkinCharacterViewModel() {
//    private val _characterListLiveData =
//        MutableLiveData< MutableList<SamodelkinCharacter> >( mutableListOf())
    private val _characterIdLiveData = MutableLiveData<UUID>()
    override val characterListLiveData: LiveData<List<SamodelkinCharacter>>
        = samodelkinRepository.getCharacters()

    override val characterLiveData = Transformations.switchMap(_characterIdLiveData) { characterId ->
        samodelkinRepository.getCharacter(characterId)
    }

    override fun addCharacter(char: SamodelkinCharacter) {
        samodelkinRepository.addCharacter(char)
    }

    override fun loadCharacter(uuid: UUID) {
        _characterIdLiveData.value = uuid
    }

    override fun generateRandomCharacter(): SamodelkinCharacter {
        return CharacterGenerator.generateRandomCharacter()
    }

//    init {
//        _characterListLiveData.value?.let { characterList ->
//            for (i in 1..20) {
//                characterList.add(generateRandomCharacter())
//            }
//            _characterListLiveData.value = characterList
//        }
//    }
}