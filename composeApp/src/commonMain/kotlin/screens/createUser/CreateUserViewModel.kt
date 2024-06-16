package screens.createUser

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.Constants.APP_ID

class CreateUserViewModel : ScreenModel {

    private val app = App.create(APP_ID)

    suspend fun createUser(user: String, pass: String, navigateBack: () -> Unit) {

        screenModelScope.launch(Dispatchers.IO) {
            try {
                app.emailPasswordAuth.registerUser(user, pass)

                withContext(Dispatchers.Main) {
                    navigateBack()
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    println(e.message)
                }
            }
        }
    }
}