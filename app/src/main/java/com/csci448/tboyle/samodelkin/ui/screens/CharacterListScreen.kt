package com.csci448.tboyle.samodelkin.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.csci448.tboyle.samodelkin.R
import com.csci448.tboyle.samodelkin.data.SamodelkinCharacter
import com.csci448.tboyle.samodelkin.util.CharacterGenerator
import com.csci448.tboyle.samodelkin.viewmodels.PreviewSamodelkinCharacterViewModel
import com.csci448.tboyle.samodelkin.viewmodels.PreviewSamodelkinCharacterViewModel.Companion.getInstance
import com.csci448.tboyle.samodelkin.viewmodels.SamodelkinCharacterViewModel
//import androidx.compose.runtime.livedata.observeAsState

@Composable
private fun CharacterRow(char: SamodelkinCharacter, onSelectCharacter: ((SamodelkinCharacter) -> Unit)) {

    Card(
        Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .clickable { onSelectCharacter(char) }) {
        Column {
            Text(String.format(stringResource(R.string.character_name_race_formatter), char.name, char.race))
            Row {
                Text(stringResource(R.string.dex_short_label), )
                Spacer(Modifier.width(2.dp))
                Text(char.dex)
                Spacer(Modifier.width(32.dp))
                Text(stringResource(R.string.str_short_label))
                Spacer(Modifier.width(2.dp))
                Text(char.str)
                Spacer(Modifier.width(32.dp))
                Text(stringResource(R.string.wis_short_label))
                Spacer(Modifier.width(2.dp))
                Text(char.wis)
            }
        }
    }
}

@Composable
fun CharacterListScreen(charList: List<SamodelkinCharacter>?, onSelectCharacter: ((SamodelkinCharacter) -> Unit)) {
    if (charList != null)
        LazyColumn {
            items(charList) { char ->
                CharacterRow(char, onSelectCharacter)
            }
        }
}

@Preview(showBackground = true)
@Composable
private fun previewStuff() {
    val vm: PreviewSamodelkinCharacterViewModel = getInstance()
    val listData = vm.characterListLiveData.observeAsState()

    CharacterListScreen(charList = listData.value, onSelectCharacter = {})
}