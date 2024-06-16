package screens.player.models

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.MongoDB
import domain.Character
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.Constants.APP_ID

enum class UiState {
    LOADING, SUCCESS, ERROR
}

class HomeViewModel : ScreenModel {

    var data = mutableStateOf(emptyList<Character>())
    private val activeUser = App.create(APP_ID).currentUser
    var uiState = mutableStateOf(UiState.LOADING)
    var playerName = mutableStateOf("")

    init {
        fetchData()
    }

    private fun fetchData(){
        screenModelScope.launch(Dispatchers.IO) {

            try {
                withContext(Dispatchers.Main) {
                    uiState.value = UiState.LOADING
                }

                if (activeUser != null) {

                    val name = MongoDB.getPLayerName()
                    if (name != null) {
                        playerName.value = name
                    } else {
                        withContext(Dispatchers.Main) {
                            uiState.value = UiState.ERROR
                            return@withContext
                        }
                    }

                    MongoDB.getCharacters().collect{
                        withContext(Dispatchers.Main) {
                            data.value = it
                            uiState.value = UiState.SUCCESS
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        uiState.value = UiState.ERROR
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    uiState.value = UiState.ERROR
                }
                println(e.message)
                delay(3000)
                fetchData()
            }
        }
    }

    suspend fun logoutUser(navigateBack: () -> Unit) {
        screenModelScope.launch {
            if (activeUser != null) {

                try {
                    withContext(Dispatchers.IO) {
                        activeUser.logOut()
                    }

                    if (!activeUser.loggedIn) {
                        withContext(Dispatchers.Main) {
                            navigateBack()
                        }
                    }else{
                        println("Error: El ususario $playerName sigue conectado")
                    }

                } catch (e: Exception) {
                    uiState.value = UiState.ERROR
                    println(e.message)

                }
            }

        }
    }
}