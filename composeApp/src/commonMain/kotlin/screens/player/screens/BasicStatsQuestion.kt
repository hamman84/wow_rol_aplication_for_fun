package screens.player.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import rol_game.composeapp.generated.resources.Res
import rol_game.composeapp.generated.resources.Throum_recruiter
import rol_game.composeapp.generated.resources.pergamino
import screens.player.components.CounterStats
import ui.theme.gameLightScheme
import util.classPhrases
import util.racePhrases

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun BasicStatsQuestion(
    typeOfClass: String,
    typeOfRace: String,
    navigateToAbilitiesScreen: () -> Unit,
    pointPerAbilityMap: MutableMap<String, Int>
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val pointsPerAbility = remember { mutableStateOf(mapOf<String, Int>()) }
    val maxPoints = 72
    val remainingPoints = remember { mutableStateOf(maxPoints) }
    val activeAdd = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(Res.drawable.Throum_recruiter),
            contentDescription = "Throum recruiter",
            contentScale = ContentScale.FillWidth,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = racePhrases(typeOfRace) + "\n" + classPhrases(typeOfClass) +
                        "\nHe visto muchos héroes caer ante la oscuridad que nos rodea. ¿Cómo has sobrevivido hasta ahora? ¿Qué te ha fortalecido en tu camino? ",
                textAlign = TextAlign.Justify
            )

        }
        Button(
            onClick = {
                showBottomSheet = true
            },
            shape = ShapeDefaults.Small
        ) {
            Text("Continuar")
        }
        if (showBottomSheet) {

            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                containerColor = Color.Transparent
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(Res.drawable.pergamino),
                        contentDescription = "paper",
                        contentScale = ContentScale.FillWidth,
                    )
                    Column(
                        modifier = Modifier.fillMaxSize().padding(top = 60.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Puntos disponibles: ${remainingPoints.value}",
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        CounterStats(
                            modifier = Modifier,
                            activeAdd = activeAdd.value,
                            abilityName = "Fuerza",
                            initialPoints = pointsPerAbility.value["Fuerza"] ?: 0,
                            onPointsChange = { newPoints ->
                                pointsPerAbility.value =
                                    pointsPerAbility.value.toMutableMap().apply {
                                        put("Fuerza", newPoints)
                                    }
                                remainingPoints.value =
                                    maxPoints - pointsPerAbility.value.values.sum()

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
                                pointsPerAbility.value =
                                    pointsPerAbility.value.toMutableMap().apply {
                                        put("Destreza", newPoints)
                                    }
                                remainingPoints.value =
                                    maxPoints - pointsPerAbility.value.values.sum()

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
                                pointsPerAbility.value =
                                    pointsPerAbility.value.toMutableMap().apply {
                                        put("Constitución", newPoints)
                                    }
                                remainingPoints.value =
                                    maxPoints - pointsPerAbility.value.values.sum()

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
                                pointsPerAbility.value =
                                    pointsPerAbility.value.toMutableMap().apply {
                                        put("Inteligencia", newPoints)
                                    }
                                remainingPoints.value =
                                    maxPoints - pointsPerAbility.value.values.sum()

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
                                pointsPerAbility.value =
                                    pointsPerAbility.value.toMutableMap().apply {
                                        put("Carisma", newPoints)
                                    }
                                remainingPoints.value =
                                    maxPoints - pointsPerAbility.value.values.sum()

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
                                pointsPerAbility.value =
                                    pointsPerAbility.value.toMutableMap().apply {
                                        put("Sabiduría", newPoints)
                                    }
                                remainingPoints.value =
                                    maxPoints - pointsPerAbility.value.values.sum()
                                activeAdd.value = remainingPoints.value != 0
                            }
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        MaterialTheme(colorScheme = gameLightScheme) {
                            ElevatedButton(
                                onClick = {
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet = false
                                        }
                                    }
                                    pointPerAbilityMap.putAll(pointsPerAbility.value)
                                    println(pointPerAbilityMap)
                                    navigateToAbilitiesScreen()
                                },
                                shape = ShapeDefaults.Small
                            ) {
                                Text("Continuar")
                            }
                        }
                    }
                }
            }

        }
    }
}
