package util

fun racePhrases(selectedRace: String): String {
    return when (selectedRace) {
        "Huargen" -> "Vuestro instinto salvaje es un recordatorio de que incluso en la oscuridad, la fuerza de la naturaleza busca su camino."
        "Draenei" -> "Vuestra presencia trae consigo el eco de tiempos mejores, aunque la oscuridad nos envuelva."
        "Gnomo" -> "En vuestras invenciones veo destellos de esperanza, pequeñas luces en la penumbra de nuestra lucha."
        "Pandaren" -> "Vuestra serenidad es un respiro en medio de la tormenta, una pausa en la lucha eterna."
        "Orco" -> "Como rivales, hemos conocido la derrota y el desespero, pero en vuestra voluntad de luchar aún veo un rayo de esperanza."
        "Elfo de la Noche" -> "Vuestra conexión con la naturaleza es un lazo que nos une en tiempos de desesperanza, una promesa de que la luz aún puede encontrar su camino entre las sombras."

        else -> " "
    }
}

fun classPhrases(selectedClass: String): String {
    return when (selectedClass) {
        "Paladín" -> "En tiempos oscuros, la esperanza es un bien escaso. Como paladín, tal vez puedas traer una chispa de luz a este mundo sombrío."
        "Guerrero" -> "La batalla parece interminable, pero incluso en la oscuridad más profunda, un guerrero con tu coraje puede ser un rayo de esperanza para los que luchan a tu lado."
        "Sacerdote" -> "En un mundo donde los dioses parecen haber abandonado a los mortales, tu conexión divina podría ser la única esperanza que queda."
        "Pícaro" -> "En los callejones sombríos y entre las sombras, la astucia es una moneda de cambio. Quizás tu habilidad para sobrevivir pueda marcar la diferencia."
        "Cazador" -> "En los bosques oscuros y los páramos desolados, tu puntería marcará la diferencia en el campo de batalla."
        "Druida" -> "En un mundo devastado por la guerra, la conexión con la naturaleza puede ser la única fuente de esperanza para los que aún creen en la vida."
        "Cazador de Demonios" -> "La batalla contra las fuerzas del mal parece imposible, pero en tus manos podría residir la clave para la salvación de este mundo asolado."
        "Chamán" -> "En medio de la devastación y la desolación, tu conexión con los elementos podría ser la única esperanza de restaurar el equilibrio en este mundo herido."
        "Brujo" -> "El poder oscuro que controlas es una espada de doble filo, pero incluso en la oscuridad más profunda, puede haber un atisbo de redención."
        "Mago" -> "En un mundo donde la magia se ha vuelto contra nosotros, tu dominio de las artes arcanas podría ser la última esperanza para cambiar nuestro destino."
        "Monje" -> "En tiempos de conflicto y desesperación, la disciplina y el equilibrio pueden ser armas poderosas contra la oscuridad que amenaza con consumirnos."
        "Caballero de la Muerte" -> "La muerte nos rodea por todas partes, muchos de los tuyos nos traicionaron, pero en medio de la desolación y la desesperación, incluso un caballero de la muerte puede encontrar un camino hacia la redención."

        else -> " "
    }
}