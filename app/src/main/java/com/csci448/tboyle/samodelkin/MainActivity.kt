package com.csci448.tboyle.samodelkin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.csci448.tboyle.samodelkin.ui.screens.CharacterDetailScreen
import com.csci448.tboyle.samodelkin.ui.screens.CharacterListScreen
import com.csci448.tboyle.samodelkin.ui.screens.NewCharacterScreen
import com.csci448.tboyle.samodelkin.ui.theme.SamodelkinTheme
import com.csci448.tboyle.samodelkin.util.CharacterGenerator
import com.csci448.tboyle.samodelkin.viewmodels.ISamodelkinCharacterViewModel
import com.csci448.tboyle.samodelkin.viewmodels.PreviewSamodelkinCharacterViewModel
import com.csci448.tboyle.samodelkin.viewmodels.SamodelkinCharacterViewModel
import com.csci448.tboyle.samodelkin.ui.navigation.SamodelkinNavHost
import com.csci448.tboyle.samodelkin.viewmodels.PreviewSamodelkinCharacterViewModel.Companion.getInstance

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val factory = SamodelkinCharacterViewModelFactory()
        val samodelkinCharacterViewModel = ViewModelProvider(this, factory)
                            .get(factory.getViewModelClass())
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityContent(samodelkinCharacterViewModel)
        }
    }
}

@Composable
private fun MainActivityContent(vm: ISamodelkinCharacterViewModel) { // changed from IS...
    val listData = vm.characterListLiveData.observeAsState()
    val navController = rememberNavController()
    SamodelkinTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SamodelkinNavHost(navController, vm)
//            CharacterListScreen(listData.value, {})
//            CharacterDetailScreen(char = CharacterGenerator.generateRandomCharacter())
//            NewCharacterScreen(
//                initialCharacter = CharacterGenerator.placeHolderCharacter(),
//                onGenerateRandomCharacter = { CharacterGenerator.generateRandomCharacter()},
//                onRequestApiCharacter = { CharacterGenerator.generateRandomCharacter() },
//                onSaveCharacter =
//            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewMainActivity() {
    val vm: PreviewSamodelkinCharacterViewModel = PreviewSamodelkinCharacterViewModel.getInstance()
//    MainActivityContent(vm = vm)
}