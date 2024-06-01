package util

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import rol_game.composeapp.generated.resources.Gnomo
import rol_game.composeapp.generated.resources.Huargen
import rol_game.composeapp.generated.resources.Orco
import rol_game.composeapp.generated.resources.Pandaren
import rol_game.composeapp.generated.resources.Res
import rol_game.composeapp.generated.resources.draenei
import rol_game.composeapp.generated.resources.night_elf

@OptIn(ExperimentalResourceApi::class)
fun getRaceImage(raceName: String): DrawableResource {
    return when(raceName){
        "Huargen" -> Res.drawable.Huargen
        "Draenei" -> Res.drawable.draenei
        "Gnomo" -> Res.drawable.Gnomo
        "Pandaren" -> Res.drawable.Pandaren
        "Orco" -> Res.drawable.Orco
        "Elfo de la Noche" -> Res.drawable.night_elf

        else -> Res.drawable.Huargen
    }

}