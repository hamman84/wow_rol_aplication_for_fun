package screens.login

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.MongoDB.app
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class LoginUiState {
    IDLE, LOADING, SUCCESS, ERROR
}

class LoginViewModel : ScreenModel {
    var uiState = mutableStateOf(LoginUiState.IDLE)
    suspend fun loginUser(
        user: String,
        pass: String,
        navigateToPlayer: () -> Unit
    ) {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main){
                    uiState.value = LoginUiState.LOADING
                }
                val emailPasswordCredentials = Credentials.emailPassword(user, pass)
                val playerLogged = app.login(emailPasswordCredentials)

                val loggedId = playerLogged.id
                println("Logged in: $loggedId")

                if (playerLogged.loggedIn) {
                    withContext(Dispatchers.Main) {
                        uiState.value = LoginUiState.SUCCESS
                        navigateToPlayer()
                    }
                }else{
                    withContext(Dispatchers.Main) {
                        uiState.value = LoginUiState.ERROR
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    println("Login failed: ${e.message}")
                }
            }
        }
    }

}