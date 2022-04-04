package com.csci448.tboyle.samodelkin.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.*
import com.csci448.tboyle.samodelkin.ui.screens.CharacterDetailScreen
import com.csci448.tboyle.samodelkin.viewmodels.ISamodelkinCharacterViewModel
import com.csci448.tboyle.samodelkin.viewmodels.SamodelkinCharacterViewModel
import java.util.*

object DetailScreenSpec: IScreenSpec {
    private const val id = "id"
    override val Route: String = "detail/{$id}"
    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(id) { type = NavType.StringType } // StringType??? says String in slides
    )

    @Composable
    override fun Content(
        viewModel: ISamodelkinCharacterViewModel,
        navController: NavController,
        navBackStackEntry: NavBackStackEntry
    ) {
        val arg = navBackStackEntry.arguments?.getString("id")
        val state = viewModel.characterLiveData.observeAsState()
        viewModel.loadCharacter(UUID.fromString(arg))
        state.value?.let{ CharacterDetailScreen(char = state.value!!)}
    }

    override fun navigateTo(vararg args: String?): String {
        return "detail/".plus(if (args.isNullOrEmpty()) "0" else args[0].toString())
    }
}