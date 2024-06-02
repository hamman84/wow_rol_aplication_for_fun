package screens.player.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.BasicStats
import domain.Character
import domain.User
import domain.abilityList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import screens.player.components.AbilityRadioButtons
import screens.player.components.ClassDropMenu
import screens.player.components.CounterStats
import screens.player.components.RaceDropMenu
import screens.player.models.CreateCharacterViewModel

class CreateCharacterScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = CreateCharacterViewModel()
        val navigator = LocalNavigator.currentOrThrow
        val snackbarHostState = remember { SnackbarHostState() }
        val charName = remember { mutableStateOf("") }
        val maxPoints = 72
        val pointsPerAbility = remember { mutableStateOf(mutableMapOf<String, Int>()) }
        val remainingPoints = remember { mutableStateOf(maxPoints) }
        val activeAdd = remember { mutableStateOf(true) }
        val selectedClass = remember { mutableStateOf("") }
        val selectedRace = remember { mutableStateOf<Pair<String, Map<String, Int>>?>(null) }
        val abilitySelected = remember { mutableListOf<String>() }
        val abilityStates = remember { mutableStateOf(abilityList.associate { it.statOwner to 0 }) }

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = charName.value,
                    onValueChange = { charName.value = it },
                    label = { Text(text = "Nombre") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ClassDropMenu(selectedClass = { classSelected ->
                    selectedClass.value = classSelected
                })

                Spacer(modifier = Modifier.height(16.dp))

                RaceDropMenu(
                    selectedRace = { raceSelected ->
                        selectedRace.value = raceSelected
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text("Puntos disponibles: ${remainingPoints.value}")

                Spacer(modifier = Modifier.height(8.dp))

                CounterStats(
                    modifier = Modifier,
                    activeAdd = activeAdd.value,
                    abilityName = "Fuerza",
                    initialPoints = pointsPerAbility.value["Fuerza"] ?: 0,
                    onPointsChange = { newPoints ->
                        pointsPerAbility.value["Fuerza"] = newPoints
                        remainingPoints.value = maxPoints - pointsPerAbility.value.values.sum()

                        activeAdd.value = remainingPoints.value != 0
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                CounterStats(
                    modifier = Modifier,
                    activeAdd = activeAdd.value,
                    abilityName = "Destreza",
                    initialPoints = pointsPerAbility.value["Destreza"] ?: 0,
                    onPointsChange = { newPoints ->
                        pointsPerAbility.value["Destreza"] = newPoints
                        remainingPoints.value = maxPoints - pointsPerAbility.value.values.sum()

                        activeAdd.value = remainingPoints.value != 0
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                CounterStats(
                    modifier = Modifier,
                    activeAdd = activeAdd.value,
                    abilityName = "Constitución",
                    initialPoints = pointsPerAbility.value["Constitución"] ?: 0,
                    onPointsChange = { newPoints ->
                        pointsPerAbility.value["Constitución"] = newPoints
                        remainingPoints.value = maxPoints - pointsPerAbility.value.values.sum()

                        activeAdd.value = remainingPoints.value != 0
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                CounterStats(
                    modifier = Modifier,
                    activeAdd = activeAdd.value,
                    abilityName = "Inteligencia",
                    initialPoints = pointsPerAbility.value["Inteligencia"] ?: 0,
                    onPointsChange = { newPoints ->
                        pointsPerAbility.value["Inteligencia"] = newPoints
                        remainingPoints.value = maxPoints - pointsPerAbility.value.values.sum()

                        activeAdd.value = remainingPoints.value != 0
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                CounterStats(
                    modifier = Modifier,
                    activeAdd = activeAdd.value,
                    abilityName = "Carisma",
                    initialPoints = pointsPerAbility.value["Carisma"] ?: 0,
                    onPointsChange = { newPoints ->
                        pointsPerAbility.value["Carisma"] = newPoints
                        remainingPoints.value = maxPoints - pointsPerAbility.value.values.sum()

                        activeAdd.value = remainingPoints.value != 0
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                CounterStats(
                    modifier = Modifier,
                    activeAdd = activeAdd.value,
                    abilityName = "Sabiduría",
                    initialPoints = pointsPerAbility.value["Sabiduría"] ?: 0,
                    onPointsChange = { newPoints ->
                        pointsPerAbility.value["Sabiduría"] = newPoints
                        remainingPoints.value = maxPoints - pointsPerAbility.value.values.sum()
                        activeAdd.value = remainingPoints.value != 0
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))

                AbilityRadioButtons(
                    abilityStates = abilityStates.value,
                    onAbilitySelected = { ability, isChecked ->
                        abilityStates.value = abilityStates.value.toMutableMap().apply {
                            if (isChecked){
                                put(ability, 2)
                                abilitySelected.add(ability)
                            }else {
                                remove(ability)
                                abilitySelected.remove(ability)
                            }
                        }
                        println(abilitySelected)
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        selectedRace.value?.let { (raceName, racialStats) ->
                            racialStats.forEach { (ability, value) ->
                                val currentPoints = pointsPerAbility.value[ability] ?: 0
                                pointsPerAbility.value[ability] = currentPoints + value
                            }
                            CoroutineScope(Dispatchers.IO).launch {
                                val embeddedUser = User().apply {
                                    characters.add(Character().apply {
                                        name = charName.value
                                        classType = selectedClass.value
                                        race = raceName
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
                                        owner = viewModel.user
                                    })
                                }
                                viewModel.createCharacter(embeddedUser)
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
