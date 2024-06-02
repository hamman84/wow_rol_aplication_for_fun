import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import io.realm.kotlin.mongodb.App
import screens.login.LoginScreen
import screens.player.screens.HomeScreen
import ui.theme.darkScheme
import ui.theme.lightScheme
import util.Constants.APP_ID


@Composable
fun App() {

    val colors by mutableStateOf(
        if (isSystemInDarkTheme()) darkScheme else lightScheme
    )

    MaterialTheme(colorScheme = colors) {
        Navigator(getStartDestination()){
            SlideTransition(it)
        }
    }
}

fun getStartDestination(): Screen {
    val user = App.create(APP_ID).currentUser
    return if (user != null) {
        HomeScreen()
    } else {
        LoginScreen()
    }
}