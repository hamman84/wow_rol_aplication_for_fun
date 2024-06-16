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
import androidx.compose.material3.OutlinedTextField
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
import ui.theme.gameLightScheme

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NameQuestion(
    textName: String,
    onTextChange: (String) -> Unit,
    navigateToSecondScreen: () -> Unit,
    snackBarError: () -> Unit
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
                text = "El veterano paladín, Thorum, te observa con una mirada penetrante desde su rincón en la taberna, y te invita con un gesto a acercarte:" +
                        "\nBienvenido, forastero. Vivimos tiempos oscuros, y los recursos escasean tanto como los aliados... Que te trae por aquí?, ¿cuál es tu nombre, joven?",
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
                            modifier = Modifier.fillMaxSize().padding(top = 60.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            OutlinedTextField(
                                value = textName,
                                onValueChange = onTextChange,
                                label = { Text(text = "Nombre") }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            ElevatedButton(
                                onClick = {
                                    if (textName.isNotEmpty()) {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            if (!sheetState.isVisible) {
                                                showBottomSheet = false
                                            }
                                        }
                                        navigateToSecondScreen()
                                    } else {
                                        snackBarError()
                                    }
                                },
                                shape = ShapeDefaults.Small
                            ) {
                                Text(text = "Continuar")
                            }
                        }
                    }
                }
            }
        }
    }
}