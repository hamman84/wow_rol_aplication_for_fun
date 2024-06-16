import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import data.MongoDB.user
import io.realm.kotlin.mongodb.App
import screens.login.LoginScreen
import screens.player.screens.HomeScreen
import ui.theme.darkScheme
import ui.theme.gameDarkScheme
import ui.theme.gameLightScheme
import ui.theme.lightScheme
import util.Constants.APP_ID


@Composable
fun App() {

    val colors by mutableStateOf(
        if (isSystemInDarkTheme()) gameDarkScheme else gameLightScheme
    )

    MaterialTheme(colorScheme = colors) {
        Navigator(getStartDestination()){
            SlideTransition(it)
        }
    }
}

fun getStartDestination(): Screen {
    return if (user?.loggedIn == true) {
        HomeScreen()
    } else {
        LoginScreen()
    }
}