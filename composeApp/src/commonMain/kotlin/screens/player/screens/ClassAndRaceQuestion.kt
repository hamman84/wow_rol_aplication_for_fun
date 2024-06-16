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
import screens.player.components.ClassDropMenu
import screens.player.components.RaceDropMenu

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun ClassAndRaceQuestion(
    pjName: String,
    selectedClass: (String) -> Unit,
    selectedRace: (Pair<String, Map<String, Int>>) -> Unit,
    navigateToThirdScreen: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
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
                text = "Thorum asiente con respeto:" +
                        "\n Un honor conocerte, $pjName. Cuéntame, ¿de qué tierras provienes?, No todo el mundo es capaz de sobrevivir en éste mundo en ruinas que nos toca vivir, ¿Cual es el camino que has decidido seguir?",
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
                        RaceDropMenu(
                            selectedRace = selectedRace
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ClassDropMenu(
                            selectedClass = selectedClass
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        ElevatedButton(
                            onClick = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }
                                navigateToThirdScreen()
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