package com.csci448.tboyle.samodelkin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.csci448.tboyle.samodelkin.ui.navigation.specs.IScreenSpec
import com.csci448.tboyle.samodelkin.viewmodels.ISamodelkinCharacterViewModel
import com.csci448.tboyle.samodelkin.viewmodels.SamodelkinCharacterViewModel

@Composable
public fun SamodelkinNavHost(navController: NavHostController, viewModel: ISamodelkinCharacterViewModel) { // navHOSTcontroller?
    NavHost(navController, IScreenSpec.startDest) { // modifier?
        IScreenSpec.screens.forEach { (route, screen) ->
            if (screen != null) {
                composable(
                    route = route!!,
                    arguments = screen.arguments
                ) { backStackEntry ->
                    screen.Content(viewModel = viewModel, navController = navController, navBackStackEntry = backStackEntry)
                }
            }
        }
    }
}
