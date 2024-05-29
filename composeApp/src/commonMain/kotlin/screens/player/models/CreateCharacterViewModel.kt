package screens.player.models

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.MongoDB
import domain.User
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.Constants.APP_ID

class CreateCharacterViewModel: ScreenModel {

    val user = App.create(APP_ID).currentUser!!.id

    suspend fun createCharacter(pj: User) {
        screenModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    MongoDB.addUser(newUser = pj.apply {
                        owner_id = user
                    })
                }

            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

}