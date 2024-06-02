package screens.createUser

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import rol_game.composeapp.generated.resources.Res
import rol_game.composeapp.generated.resources.pj_creation_bg
import screens.login.LoginScreen
import ui.theme.lightScheme

class CreateUserScreen : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { CreateUserViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val scope = rememberCoroutineScope()
        val user = remember { mutableStateOf("") }
        val pass = remember { mutableStateOf("") }
        val snackbarHostState = remember { SnackbarHostState() }

        MaterialTheme(colorScheme = lightScheme) {

            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
            ) {

                Box(modifier = Modifier.fillMaxSize()) {

                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(Res.drawable.pj_creation_bg),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier.fillMaxSize().padding(vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        TextField(
                            value = user.value,
                            onValueChange = { user.value = it },
                            label = { Text("Usuario") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = pass.value,
                            onValueChange = { pass.value = it },
                            label = { Text("Contraseña") }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        ElevatedButton(
                            onClick = {
                                scope.launch {
                                    try {
                                        viewModel.createUser(
                                            user.value,
                                            pass.value
                                        ){
                                            navigator.push(LoginScreen())
                                        }
                                        snackbarHostState.showSnackbar("Usuario creado")

                                    }catch (e: Exception){
                                        snackbarHostState.showSnackbar("Error al crear usuario: ${e.message}")
                                    }

                                }
                            }
                        ) {
                            Text("Crear cuenta")
                        }
                    }
                }
            }
        }
    }
}