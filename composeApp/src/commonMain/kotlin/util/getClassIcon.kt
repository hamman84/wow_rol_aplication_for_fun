package util

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import rol_game.composeapp.generated.resources.Res
import rol_game.composeapp.generated.resources.brujo
import rol_game.composeapp.generated.resources.death_knight
import rol_game.composeapp.generated.resources.demon_hunter
import rol_game.composeapp.generated.resources.druid
import rol_game.composeapp.generated.resources.evocator
import rol_game.composeapp.generated.resources.hunter
import rol_game.composeapp.generated.resources.mague
import rol_game.composeapp.generated.resources.monk
import rol_game.composeapp.generated.resources.paladin
import rol_game.composeapp.generated.resources.rogue
import rol_game.composeapp.generated.resources.sacer
import rol_game.composeapp.generated.resources.warrior
import rol_game.composeapp.generated.resources.xhaman

@OptIn(ExperimentalResourceApi::class)
fun getClassIcon(className: String): DrawableResource{
    return when(className){
        "Paladin" -> Res.drawable.paladin
        "Guerrero" -> Res.drawable.warrior
        "Sacerdote" -> Res.drawable.sacer
        "Pícaro" -> Res.drawable.rogue
        "Cazador" -> Res.drawable.hunter
        "Druida" -> Res.drawable.druid
        "Cazador de Demonios" -> Res.drawable.demon_hunter
        "Chamán" -> Res.drawable.xhaman
        "Brujo" -> Res.drawable.brujo
        "Mago" -> Res.drawable.mague
        "Monje" -> Res.drawable.monk
        "Caballero de la Muerte" -> Res.drawable.death_knight
        "Evocador" -> Res.drawable.evocator

        else -> Res.drawable.paladin
    }
}