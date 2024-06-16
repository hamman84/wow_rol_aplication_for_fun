package util

fun getAbilitiesMaxCount(className: String): Int {
    return when (className) {
        "Paladín" -> 2
        "Guerrero" -> 2
        "Sacerdote" -> 2
        "Pícaro" -> 4
        "Cazador" -> 3
        "Druida" -> 2
        "Cazador de Demonios" -> 3
        "Chamán" -> 2
        "Brujo" -> 2
        "Mago" -> 2
        "Monje" -> 2
        "Caballero de la Muerte" -> 2
        else -> 0
    }
}