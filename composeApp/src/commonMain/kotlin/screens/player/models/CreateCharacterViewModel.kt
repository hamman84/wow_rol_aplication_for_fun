package screens.player.models

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.MongoDB
import domain.Character
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.Constants.APP_ID

class CreateCharacterViewModel: ScreenModel {

    val userId = App.create(APP_ID).currentUser!!.id

    suspend fun createPlayerAndCharacter(userId: String, newCharacter: Character) {
        screenModelScope.launch {
            try {
                withContext(Dispatchers.IO) {

                    MongoDB.upsertPlayer(userId, newCharacter)

                }

            } catch (e: Exception) {
                println("Error creating player and character: ${e.message}")
            }
        }
    }

}