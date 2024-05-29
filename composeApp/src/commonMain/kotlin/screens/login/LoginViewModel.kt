package screens.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.Constants

class LoginViewModel : ScreenModel {

    private val app = App.create(Constants.APP_ID)

    suspend fun loginUser(
        user: String,
        pass: String,
        navigateToPlayer: () -> Unit
    ) {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                val emailPasswordCredentials = Credentials.emailPassword(user, pass)
                app.login(emailPasswordCredentials)

                withContext(Dispatchers.Main) {
                    navigateToPlayer()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    println(e.message)
                }
            }
        }
    }

}