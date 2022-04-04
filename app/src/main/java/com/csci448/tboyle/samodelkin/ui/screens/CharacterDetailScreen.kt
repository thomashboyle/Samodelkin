package com.csci448.tboyle.samodelkin.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.tboyle.samodelkin.R
import com.csci448.tboyle.samodelkin.data.SamodelkinCharacter
import com.csci448.tboyle.samodelkin.util.CharacterGenerator

@Composable
private fun StatsDisplay(stat_name: String, stat_value: String) {
    Row {
        Text(stat_name, Modifier.weight(1.0f))
        Text(stat_value, Modifier.weight(1.0f))
    }
}

@Composable
private fun SectionHeader(header_title: String) {
    Column {
        Text(header_title, fontSize=24.sp)
        Divider(thickness=2.dp)
    }
}

@Composable
private fun StatsSection(dex: String, wis: String, str: String) {
    Column {
        SectionHeader(header_title = stringResource(R.string.stats_label))
        StatsDisplay(stat_name = stringResource(R.string.dex_label), stat_value = dex)
        StatsDisplay(stat_name = stringResource(R.string.str_label), stat_value = str)
        StatsDisplay(stat_name = stringResource(R.string.wis_label), stat_value = wis)
    }
}

@Composable
private fun RaceSection(race: String) {
    Column {
        SectionHeader(header_title = stringResource(R.string.race_label))
        Text(race)
    }
}

@Composable
private fun NameSection(name: String) {
    Column {
        SectionHeader(header_title = stringResource(R.string.name_label))
        Text(name)
    }
}

@Composable
public fun CharacterDetailScreen(char: SamodelkinCharacter) {
    Column(Modifier.padding(16.dp)) {
        NameSection(name = char.name)
        Spacer(Modifier.height(16.dp))
        RaceSection(race = char.race)
        Spacer(Modifier.height(16.dp))
        StatsSection(dex = char.dex, wis = char.wis, str = char.str)
        
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCharacterDetailScreen() {
//    StatsSection(dex = "14", wis = "16", str = "12")
//    RaceSection(race = "Still Testing")
//    NameSection(name = "Testiiiiing")
    CharacterDetailScreen(char = CharacterGenerator.generateRandomCharacter())
}