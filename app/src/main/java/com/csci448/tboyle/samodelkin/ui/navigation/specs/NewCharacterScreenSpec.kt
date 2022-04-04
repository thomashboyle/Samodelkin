package com.csci448.tboyle.samodelkin.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.csci448.tboyle.samodelkin.R
import com.csci448.tboyle.samodelkin.ui.screens.NewCharacterScreen
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
        NewCharacterScreen(
            initialCharacter = viewModel.generateRandomCharacter(),
            onGenerateRandomCharacter = { viewModel.generateRandomCharacter() },
            onRequestApiCharacter = { viewModel.generateRandomCharacter() },
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