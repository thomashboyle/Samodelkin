package com.csci448.tboyle.samodelkin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.csci448.tboyle.samodelkin.ui.navigation.specs.IScreenSpec

@Composable
fun SamodelkinTopBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    IScreenSpec.TopBar(navController = navController, navBackStackEntry = navBackStackEntry)
}