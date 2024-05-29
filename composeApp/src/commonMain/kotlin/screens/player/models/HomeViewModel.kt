package screens.player.models

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.MongoDB
import domain.Character
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.Constants.APP_ID

class HomeViewModel : ScreenModel {

    var data = mutableStateOf(emptyList<Character>())
    val activeUser = App.create(APP_ID).currentUser

    init {

        screenModelScope.launch(Dispatchers.IO) {

            try {
                MongoDB.getCharacters().collect {
                    withContext(Dispatchers.Main) {
                        data.value = it
                    }
                }


            } catch (e: Exception) {
                println(e.message)
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
                    withContext(Dispatchers.Main) {
                        navigateBack()
                    }

                } catch (e: Exception) {

                    println(e.message)

                }
            }

        }
    }

}