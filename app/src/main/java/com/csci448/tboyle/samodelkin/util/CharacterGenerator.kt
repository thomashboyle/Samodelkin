package com.csci448.tboyle.samodelkin.util

import com.csci448.tboyle.samodelkin.data.SamodelkinCharacter

object CharacterGenerator {
    private fun <T> List<T>.rand() = shuffled().first()
    private fun Int.roll(sides: Int = 6) = (0 until this)
        .map { (1..sides).toList().rand() }
        .sum()
        .toString()
    private val firstNames = listOf("Eli", "Alex", "Sophie", "Thorin", "Bilbo", "Fred", "Thorn")
    private val lastNames = listOf("Lightweaver", "Greatfoot", "Oakenfield", "Oakenshield", "Baggins")
    private val races = listOf("Dwarf", "Elf", "Human", "Halfling", "Gnome", "Troll", "Goblin", "Kobold", "Ent")
    private fun name() = "${firstNames.rand()} ${lastNames.rand()}"
    private fun race() = races.rand()
    private fun dex() = 4.roll(7)
    private fun wis() = 3.roll(8)
    private fun str() = 5.roll(6)
    fun placeHolderCharacter() = SamodelkinCharacter("Generating Character...", "", "", "", "")
    fun generateRandomCharacter() = SamodelkinCharacter(name = name(), race = race(), dex = dex(), wis = wis(), str =
    str())

}