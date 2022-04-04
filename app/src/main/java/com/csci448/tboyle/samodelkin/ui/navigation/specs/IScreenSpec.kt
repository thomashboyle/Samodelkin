package com.csci448.tboyle.samodelkin.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.csci448.tboyle.samodelkin.viewmodels.ISamodelkinCharacterViewModel
import com.csci448.tboyle.samodelkin.viewmodels.SamodelkinCharacterViewModel

sealed interface IScreenSpec {
    val Route: String
    val arguments: List<NamedNavArgument>
    @Composable
    fun Content(viewModel: ISamodelkinCharacterViewModel, navController: NavController, navBackStackEntry: NavBackStackEntry)
    fun navigateTo(vararg args: String?): String

    companion object {
        val startDest = ListScreenSpec.navigateTo()
        val screens = IScreenSpec::class.sealedSubclasses.associate {it.objectInstance?.Route to it.objectInstance}
    }

}