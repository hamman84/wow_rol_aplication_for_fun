package screens.player.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.BasicStats
import domain.Character
import domain.abilityList
import io.realm.kotlin.ext.realmDictionaryOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import screens.player.models.CreateCharacterViewModel

class CreateCharacterScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { CreateCharacterViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val userId = viewModel.userId
        val showFirstScreen = remember { mutableStateOf(true) }
        val showSecondScreen = remember { mutableStateOf(false) }
        val showThirdScreen = remember { mutableStateOf(false) }
        val showFourthScreen = remember { mutableStateOf(false) }
        val showFifthScreen = remember { mutableStateOf(false) }
        val showLastScreen = remember { mutableStateOf(false) }
        val charName = remember { mutableStateOf("") }
        val selectedClass = remember { mutableStateOf("") }
        val selectedRace = remember { mutableStateOf<Pair<String, Map<String, Int>>?>(null) }
        val pointsPerAbility = remember { mutableStateOf(mutableMapOf<String, Int>()) }
        val abilitySelected = remember { mutableMapOf<String, Int>() }
        val abilityStates = remember { mutableStateOf(abilityList.associate { it.statOwner to 0 }) }
        val disabledAbilities = remember { mutableStateOf(setOf<String>()) }


        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                IconButton(onClick = { navigator.push(HomeScreen()) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Atrás"
                    )
                }
            }
        ) {
            if (showFirstScreen.value) {
                NameQuestion(
                    textName = charName.value,
                    onTextChange = { charName.value = it },
                    navigateToSecondScreen = {
                        showFirstScreen.value = false
                        showSecondScreen.value = true
                    },
                    snackBarError = {
                        scope.launch {
                            snackbarHostState.showSnackbar("El nombre no puede estar vacío")
                        }
                    }
                )
            }
            if (showSecondScreen.value) {
                ClassAndRaceQuestion(
                    selectedClass = { classSelected ->
                        selectedClass.value = classSelected
                    },
                    selectedRace = { raceSelected ->
                        selectedRace.value = raceSelected
                    },
                    pjName = charName.value,
                    navigateToThirdScreen = {
                        showSecondScreen.value = false
                        showThirdScreen.value = true
                    }
                )
            }
            if (showThirdScreen.value) {
                selectedRace.value?.let { it1 ->
                    BasicStatsQuestion(
                        typeOfClass = selectedClass.value,
                        typeOfRace = it1.first,
                        pointPerAbilityMap = pointsPerAbility.value,
                        navigateToAbilitiesScreen = {
                            showThirdScreen.value = false
                            showFourthScreen.value = true
                        }
                    )
                }
            }
            if (showFourthScreen.value) {
                ClassAbilityQuestion(
                    selectedClass = selectedClass.value,
                    onAbilitySelected = { ability, isChecked ->
                        abilityStates.value = abilityStates.value.toMutableMap().apply {
                            if (isChecked) {
                                put(ability, 2)
                                abilitySelected[ability] = 2
                                disabledAbilities.value = disabledAbilities.value.plus(ability)
                            } else {
                                remove(ability)
                                abilitySelected.remove(ability)
                                disabledAbilities.value = disabledAbilities.value.minus(ability)
                            }
                        }

                        println(abilitySelected)
                        println(disabledAbilities)
                        println(disabledAbilities.value.size)

                    },
                    abilityStates = abilityStates.value,
                    navigateToScreen = {
                        showFourthScreen.value = false
                        showFifthScreen.value = true
                    }
                )
            }
            if (showFifthScreen.value) {
                BackgroundAbilityQuestion(
                    disabledAbilities = disabledAbilities.value,
                    abilityStates = abilityStates.value,
                    onAbilitySelected = { ability, isChecked ->
                        abilityStates.value = abilityStates.value.toMutableMap().apply {
                            if (isChecked) {
                                put(ability, 2)
                                abilitySelected[ability] = 2
                            } else {
                                remove(ability)
                                abilitySelected.remove(ability)
                            }
                        }
                        println(abilitySelected)
                    },
                    navigateToScreen = {
                        showFifthScreen.value = false
                        showLastScreen.value = true
                    }
                )
            }
            if (showLastScreen.value) {
                Button(
                    onClick = {
                        selectedRace.value?.let { (raceName, racialStats) ->
                            racialStats.forEach { (ability, value) ->
                                val currentPoints = pointsPerAbility.value[ability] ?: 0
                                pointsPerAbility.value[ability] = currentPoints + value
                            }
                            CoroutineScope(Dispatchers.IO).launch {
                                val newCharacter = Character().apply {
                                    name = charName.value
                                    classType = selectedClass.value
                                    race = raceName
                                    competencies = realmDictionaryOf()
                                    abilitySelected.forEach { (ability, value) ->
                                        competencies[ability] = value
                                    }
                                    luckScore = 0
                                    level = 1
                                    experience = 60
                                    stats = BasicStats().apply {
                                        strength = pointsPerAbility.value["Fuerza"] ?: 0
                                        dexterity = pointsPerAbility.value["Destreza"] ?: 0
                                        constitution =
                                            pointsPerAbility.value["Constitución"] ?: 0
                                        intelligence =
                                            pointsPerAbility.value["Inteligencia"] ?: 0
                                        charisma = pointsPerAbility.value["Carisma"] ?: 0
                                        wisdom = pointsPerAbility.value["Sabiduría"] ?: 0
                                    }
                                }

                                viewModel.createPlayerAndCharacter(userId = userId, newCharacter = newCharacter)
                            }
                            navigator.pop()
                        }
                    }
                ) {
                    Text(text = "Crear Personaje")
                }
            }
        }
    }
}

