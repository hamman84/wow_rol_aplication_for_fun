package screens.player.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import domain.Character

data class CharacterScreen(val character: Character) : Screen {
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text("Pantalla del personaje: ${character.name}")
            Text("clase: ${character.classType}")
            Text("raza: ${character.race}")
            Text("estadísticas: " +
                    "\n Fuerza:${character.stats?.strength}" +
                    "\n Destreza:${character.stats?.dexterity}" +
                    "\n Inteligencia:${character.stats?.intelligence}"
            )
        }
    }
}