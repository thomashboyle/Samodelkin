package com.csci448.tboyle.samodelkin.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.tboyle.samodelkin.R
import com.csci448.tboyle.samodelkin.data.SamodelkinCharacter
import com.csci448.tboyle.samodelkin.util.CharacterGenerator
import com.csci448.tboyle.samodelkin.util.NetworkConnectionUtil

@Composable
fun NewCharacterScreen(characterState: MutableState<SamodelkinCharacter>,
                       onGenerateRandomCharacter: () -> Unit,
                       onRequestApiCharacter: () -> Unit,
                       onSaveCharacter: (SamodelkinCharacter) -> Unit) {
//    val characterDataState = rememberSaveable { mutableStateOf(initialCharacter) }
    val character = characterState.value
    val portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    if(portrait) {
        Column {
            CharacterCard(character)
            Spacer(Modifier.height(16.dp))
            Row {
                Box(Modifier.weight(.5f)) {
                    GenerateRandomCharacterButton(character, onGenerateRandomCharacter)
                }
                Spacer(Modifier.width(16.dp))
                Box(Modifier.weight(.5f)) {
                    ApiCharacterButton(character, onRequestApiCharacter)
                }
            }
            Spacer(Modifier.height(16.dp))
            SaveCharacterButton(character, onSaveCharacter)
        }
    } else {
        // in landscape orientation
        Row (Modifier.padding(16.dp)){
            Box(Modifier.weight(.5f)) {
                CharacterCard(character)
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(.5f)) {
                GenerateRandomCharacterButton(character, onGenerateRandomCharacter)
                Spacer(Modifier.height(16.dp))
                ApiCharacterButton(character, onRequestApiCharacter)
                Spacer(Modifier.height(16.dp))
                SaveCharacterButton(character, onSaveCharacter)
            }
        }
    }
}

@Composable
private fun CharacterCard(charState: SamodelkinCharacter) {
    Card {
        CharacterDetailScreen(char = charState)
    }
}

@Composable
private fun GenerateRandomCharacterButton(
    characterDataState: SamodelkinCharacter,
    onGenerateRandomCharacter: () -> Unit
) {
    NewCharacterButton(stringResource(R.string.generate_new_random_label), buttonOnClick = {characterDataState = onGenerateRandomCharacter()})
}

@Composable
private fun NewCharacterButton(display_text: String,
                               buttonEnabled: Boolean = true,
                               buttonOnClick: () -> Unit) {
    Button(modifier=Modifier.fillMaxWidth(),
        enabled = buttonEnabled,
        onClick = buttonOnClick) {
        Text(display_text, textAlign = TextAlign.Center)
    }
}

@Composable
private fun ApiCharacterButton(characterDataState: SamodelkinCharacter,
                               onRequestApiCharacter: () -> Unit) {
    NewCharacterButton(
        stringResource(R.string.api_label),
        buttonEnabled= NetworkConnectionUtil
            .isNetworkAvailableAndConnected(LocalContext.current),
        buttonOnClick = {characterDataState = onRequestApiCharacter()})
}

@Composable
private fun SaveCharacterButton(characterDataState: SamodelkinCharacter,
                                onSaveCharacter: (SamodelkinCharacter) -> Unit) {
    NewCharacterButton(display_text = stringResource(R.string.save_to_codex_label), buttonEnabled = false, buttonOnClick = {onSaveCharacter(characterDataState)})
}

@Preview(showBackground = true)
@Composable
private fun PreviewNewCharacterScreen() {
    NewCharacterScreen(initialCharacter = CharacterGenerator.placeHolderCharacter(), {CharacterGenerator.generateRandomCharacter()}, {CharacterGenerator.generateRandomCharacter()}, {})
}

@Preview(widthDp = 1024, heightDp = 720, showBackground = true, device = Devices.AUTOMOTIVE_1024p)
@Composable
private fun PreviewNewCharacterScreenLandscape() {
    NewCharacterScreen(initialCharacter = CharacterGenerator.placeHolderCharacter(), {CharacterGenerator.generateRandomCharacter()}, {CharacterGenerator.generateRandomCharacter()}, {})
}

