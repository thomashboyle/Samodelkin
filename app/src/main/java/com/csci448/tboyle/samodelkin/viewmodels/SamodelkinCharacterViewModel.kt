package com.csci448.tboyle.samodelkin.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.csci448.tboyle.samodelkin.data.SamodelkinCharacter
import com.csci448.tboyle.samodelkin.data.database.SamodelkinRepository
import com.csci448.tboyle.samodelkin.util.CharacterGenerator
import com.csci448.tboyle.samodelkin.util.CharacterWorker
import java.util.*

class SamodelkinCharacterViewModel (
    private val samodelkinRepository: SamodelkinRepository,
    context: Context
    ): ISamodelkinCharacterViewModel() {
    private val workManager = WorkManager.getInstance(context)
    private val workRequest = CharacterWorker.buildOneTimeWorkRequest()
    override val outputWorkerInfo: LiveData<WorkInfo> =
        workManager.getWorkInfoByIdLiveData(workRequest.id)
    override fun requestWebCharacter() {
        workManager.enqueueUniqueWork(CharacterWorker.UNIQUE_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            workRequest)
    }
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