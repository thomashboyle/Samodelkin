package com.csci448.tboyle.samodelkin.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.*
import com.csci448.tboyle.samodelkin.ui.screens.CharacterListScreen
import com.csci448.tboyle.samodelkin.viewmodels.ISamodelkinCharacterViewModel
import com.csci448.tboyle.samodelkin.viewmodels.SamodelkinCharacterViewModel

object ListScreenSpec: IScreenSpec {
    override val Route: String = "list"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun navigateTo(vararg args: String?): String {
        return Route
    }
    @Composable
    override fun Content(
        viewModel: ISamodelkinCharacterViewModel,
        navController: NavController,
        navBackStackEntry: NavBackStackEntry
    ) {
        val state = viewModel.characterListLiveData.observeAsState()
        CharacterListScreen(charList = state.value, onSelectCharacter = { char ->
            navController.navigate(DetailScreenSpec.navigateTo(char.id.toString()))
        })
    }
}