package screens.player.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import screens.player.components.AbilityClassCheckBox
import ui.theme.gameLightScheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun ClassAbilityQuestion(
    selectedClass: String,
    abilityStates: Map<String, Int>,
    onAbilitySelected: (String, Boolean) -> Unit,
    navigateToScreen: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(Res.drawable.Throum_recruiter),
            contentDescription = "Thorum recruiter",
            contentScale = ContentScale.FillWidth,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Que pasa con tus habilidades?",
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

            MaterialTheme(colorScheme = gameLightScheme) {
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
                            modifier = Modifier.fillMaxSize().padding(top = 50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AbilityClassCheckBox(
                                modifier = Modifier.padding(top = 16.dp),
                                selectedClass = selectedClass,
                                onAbilitySelected = { stat, isChecked ->
                                    onAbilitySelected(stat, isChecked)
                                },
                                abilityStates = abilityStates
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            ElevatedButton(
                                onClick = {
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet = false
                                        }
                                    }
                                    navigateToScreen()
                                          },
                                shape = ShapeDefaults.Small
                            ) {
                                Text(text = "Seguir")
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowForward,
                                    contentDescription = "Seguir"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}