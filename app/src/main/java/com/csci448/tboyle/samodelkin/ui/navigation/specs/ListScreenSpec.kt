package com.csci448.tboyle.samodelkin.ui.navigation.specs

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import com.csci448.tboyle.samodelkin.R
import com.csci448.tboyle.samodelkin.ui.screens.CharacterListScreen
import com.csci448.tboyle.samodelkin.viewmodels.ISamodelkinCharacterViewModel
import com.csci448.tboyle.samodelkin.viewmodels.SamodelkinCharacterViewModel

object ListScreenSpec: IScreenSpec {
    override val title: Int = R.string.app_name
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

    @Composable
    override fun TopAppBarActions(navController: NavController) {
        IconButton(onClick = {navController.navigate(NewCharacterScreenSpec.navigateTo())}) {
            Icon(imageVector = Icons.Filled.AddCircle, contentDescription = stringResource(R.string.menu_add_character_desc))
        }
    }
}