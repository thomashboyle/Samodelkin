package com.csci448.tboyle.samodelkin.ui.navigation.specs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.work.WorkInfo
import com.csci448.tboyle.samodelkin.R
import com.csci448.tboyle.samodelkin.data.SamodelkinCharacter
import com.csci448.tboyle.samodelkin.ui.screens.NewCharacterScreen
import com.csci448.tboyle.samodelkin.util.CharacterWorker
import com.csci448.tboyle.samodelkin.viewmodels.ISamodelkinCharacterViewModel
import com.csci448.tboyle.samodelkin.viewmodels.SamodelkinCharacterViewModel

object NewCharacterScreenSpec: IScreenSpec {
    override val title: Int = R.string.app_name
    override val Route: String = "New"
    override val arguments: List<NamedNavArgument> = emptyList()

    @Composable
    override fun Content(
        viewModel: ISamodelkinCharacterViewModel,
        navController: NavController,
        navBackStackEntry: NavBackStackEntry
    ) {
        val characterState = rememberSaveable {
            mutableStateOf( viewModel.generateRandomCharacter())
        }
        val workInfoState = viewModel.outputWorkerInfo.observeAsState()
        workInfoState.value?.let { workInfo ->
            when(workInfo.state) {
                WorkInfo.State.ENQUEUED -> Log.d(LOG_TAG, "workInfo enqueued")
                WorkInfo.State.RUNNING -> Log.d(LOG_TAG, "workInfo running")
                WorkInfo.State.SUCCEEDED -> {
                    Log.d(LOG_TAG, "workInfo succeeded")
                    val	apiData	=	CharacterWorker.getApiData(	workInfo.outputData	)
                    Log.d(LOG_TAG,	"Got	api	data	$apiData")
                    if(apiData != null) {
                        val (race, name, dex, wis, str) = apiData.split(",")
                        val apiCharacter = SamodelkinCharacter(name, race, dex, wis, str )
                        characterState.value = apiCharacter
                    }
                }
                else -> Log.d(LOG_TAG, "other workInfo state")
            }
        }
        NewCharacterScreen(
            characterState = characterState,
            onGenerateRandomCharacter = {
                characterState.value = viewModel.generateRandomCharacter() },
            onRequestApiCharacter = { viewModel.requestWebCharacter() },
            onSaveCharacter = { char ->
                viewModel.addCharacter(char)
                navController.popBackStack(ListScreenSpec.navigateTo(), inclusive=false, saveState=false)
            }
        )
    }

    override fun navigateTo(vararg args: String?): String {
        return Route
    }

    @Composable
    override fun TopAppBarActions(navController: NavController) {}


}