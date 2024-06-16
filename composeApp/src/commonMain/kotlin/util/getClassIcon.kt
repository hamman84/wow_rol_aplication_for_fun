package util

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import rol_game.composeapp.generated.resources.Res
import rol_game.composeapp.generated.resources.brujoIcon
import rol_game.composeapp.generated.resources.death_KnightIcon
import rol_game.composeapp.generated.resources.demon_hunterIcon
import rol_game.composeapp.generated.resources.druidIcon
import rol_game.composeapp.generated.resources.hunterIcon
import rol_game.composeapp.generated.resources.magueIcon
import rol_game.composeapp.generated.resources.monkIcon
import rol_game.composeapp.generated.resources.paladinIcon
import rol_game.composeapp.generated.resources.rogueIcon
import rol_game.composeapp.generated.resources.sacerIcon
import rol_game.composeapp.generated.resources.warriorIcon
import rol_game.composeapp.generated.resources.xhamanIcon


@OptIn(ExperimentalResourceApi::class)
fun getClassIcon(className: String): DrawableResource {
    return when(className){
        "Paladin" -> Res.drawable.paladinIcon
        "Guerrero" -> Res.drawable.warriorIcon
        "Sacerdote" -> Res.drawable.sacerIcon
        "Pícaro" -> Res.drawable.rogueIcon
        "Cazador" -> Res.drawable.hunterIcon
        "Druida" -> Res.drawable.druidIcon
        "Cazador de Demonios" -> Res.drawable.demon_hunterIcon
        "Chamán" -> Res.drawable.xhamanIcon
        "Brujo" -> Res.drawable.brujoIcon
        "Mago" -> Res.drawable.magueIcon
        "Monje" -> Res.drawable.monkIcon
        "Caballero de la Muerte" -> Res.drawable.death_KnightIcon

        else -> Res.drawable.paladinIcon
    }
}