package domain

import org.jetbrains.compose.resources.ExperimentalResourceApi
import rol_game.composeapp.generated.resources.Gnomo
import rol_game.composeapp.generated.resources.Huargen
import rol_game.composeapp.generated.resources.Orco
import rol_game.composeapp.generated.resources.Pandaren
import rol_game.composeapp.generated.resources.Res
import rol_game.composeapp.generated.resources.draenei
import rol_game.composeapp.generated.resources.night_elf

@OptIn(ExperimentalResourceApi::class)
val raceList = listOf(
    Race(
        raceName = "Huargen",
        raceImage = mapOf(
            "Huargen" to Res.drawable.Huargen
        ),
        racialStats = mapOf(
            "Fuerza" to 2,
            "Constitución" to 1
        )
    ),
    Race(
        raceName = "Draenei",
        raceImage = mapOf(
            "Draenei" to Res.drawable.draenei
        ),
        racialStats = mapOf(
            "Carisma" to 2,
            "Fuerza" to 1
        )
    ),
    Race(
        raceName = "Gnomo",
        raceImage = mapOf(
            "Gnomo" to Res.drawable.Gnomo
        ),
        racialStats = mapOf(
            "Inteligencia" to 2,
            "Destreza" to 1
        )
    ),
    Race(
        raceName = "Pandaren",
        raceImage = mapOf(
            "Pandaren" to Res.drawable.Pandaren
        ),
        racialStats = mapOf(
            "Sabiduría" to 2,
            "Destreza" to 1
        )
    ),
    Race(
        raceName = "Orco",
        raceImage = mapOf(
            "Orco" to Res.drawable.Orco
        ),
        racialStats = mapOf(
            "Fuerza" to 2,
            "Constitución" to 1
        )
    ),
    Race(
        raceName = "Elfo de la Noche",
        raceImage = mapOf(
            "Elfo de la Noche" to Res.drawable.night_elf
        ),
        racialStats = mapOf(
            "Destreza" to 2,
            "Sabiduría" to 1
        )
    )
)