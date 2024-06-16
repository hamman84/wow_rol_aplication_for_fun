package screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import rol_game.composeapp.generated.resources.Res
import rol_game.composeapp.generated.resources.login_screen
import screens.createUser.CreateUserScreen
import screens.player.screens.HomeScreen
import ui.theme.darkScheme

class LoginScreen : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val loginViewModel = navigator.rememberNavigatorScreenModel { LoginViewModel() }
        val userText = remember { mutableStateOf("") }
        val passwordText = remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        val uiState = loginViewModel.uiState.value


        MaterialTheme(colorScheme = darkScheme) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(Res.drawable.login_screen),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = userText.value,
                        onValueChange = { userText.value = it },
                        label = { Text("Usuario") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = passwordText.value,
                        onValueChange = { passwordText.value = it },
                        label = { Text("Contraseña") }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    ElevatedButton(onClick = {
                        scope.launch {
                            loginViewModel.loginUser(
                                user = userText.value,
                                pass = passwordText.value,
                                navigateToPlayer = { navigator.push(HomeScreen()) }
                            )
                        }
                    }) {
                        if (uiState == LoginUiState.LOADING) {
                            CircularProgressIndicator(
                                modifier = Modifier.height(16.dp).width(16.dp)
                            )
                        } else {
                            Text(text = "Login")
                        }
                    }
                    when (uiState) {
                        LoginUiState.ERROR -> Text(text = "Error al iniciar sesión")
                        else -> Unit
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        modifier = Modifier.clickable { navigator.push(CreateUserScreen()) },
                        text = "No tienes cuenta?, clica aquí para regístrarte",
                        color = Color.White
                    )
                }
            }
        }
    }
}