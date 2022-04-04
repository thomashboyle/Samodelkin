package com.csci448.tboyle.samodelkin.ui.navigation.specs

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.csci448.tboyle.samodelkin.R
import com.csci448.tboyle.samodelkin.viewmodels.ISamodelkinCharacterViewModel
import com.csci448.tboyle.samodelkin.viewmodels.SamodelkinCharacterViewModel

sealed interface IScreenSpec {
    val title: Int
    val Route: String
    val arguments: List<NamedNavArgument>
    @Composable
    fun Content(viewModel: ISamodelkinCharacterViewModel, navController: NavController, navBackStackEntry: NavBackStackEntry)
    fun navigateTo(vararg args: String?): String

    companion object {
        val startDest = ListScreenSpec.navigateTo()
        val screens = IScreenSpec::class.sealedSubclasses.associate {it.objectInstance?.Route to it.objectInstance}
        @Composable
        fun TopBar(navController: NavController, navBackStackEntry: NavBackStackEntry?) {
            val route: String = navBackStackEntry?.destination?.route ?: ""
            screens[route]?.TopAppBarContent(navController  )
        }
    }

    @Composable
    fun TopAppBarActions(navController: NavController)

    @Composable
    private fun TopAppBarContent(navController: NavController) {
        TopAppBar(
            navigationIcon = if( navController.previousBackStackEntry != null ) {
                 -> IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.menu_back_desc)
                    )
                }
            } else {
                null
            },
            title = { Text(stringResource(title))},
            actions = { TopAppBarActions(navController = navController)})
    }
}