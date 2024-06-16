package domain

val classAbiltyList = listOf(
    ClassAbility(
        classType = "Druida",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Arcanos" to 0,
                    "Naturaleza" to 0,
                    "Religion" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Sabiduría",
                abilityClass = mapOf(
                    "Trato con Animales" to 0,
                    "Perspicacia" to 0,
                    "Medicina" to 0,
                    "Percepción" to 0,
                    "Supervivencia" to 0
                )
            )
        )
    ),
    ClassAbility(
        classType = "Cazador",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Fuerza",
                abilityClass = mapOf(
                    "Atletismo" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Destreza",
                abilityClass = mapOf(
                    "Sigilo" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Investigación" to 0,
                    "Naturaleza" to 0,
                )
            ),
            CharacterAbility(
                statOwner = "Sabiduría",
                abilityClass = mapOf(
                    "Medicina" to 0,
                    "Percepción" to 0,
                    "Perspicacia" to 0,
                    "Supervivencia" to 0,
                    "Trato con Animales" to 0
                )
            )
        )
    ),
    ClassAbility(
        classType = "Mago",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Arcanos" to 0,
                    "Historia" to 0,
                    "Religión" to 0,
                    "Investigación" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Sabiduría",
                abilityClass = mapOf(
                    "Perspicacia" to 0,
                    "Medicina" to 0
                )
            )
        )
    ),
    ClassAbility(
        classType = "Paladín",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Fuerza",
                abilityClass = mapOf(
                    "Atletismo" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Religión" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Sabiduría",
                abilityClass = mapOf(
                    "Perspicacia" to 0,
                    "Medicina" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Carisma",
                abilityClass = mapOf(
                    "Persuasión" to 0
                )
            )
        )
    ),
    ClassAbility(
        classType = "Sacerdote",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Historia" to 0,
                    "Religión" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Sabiduría",
                abilityClass = mapOf(
                    "Perspicacia" to 0,
                    "Medicina" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Carisma",
                abilityClass = mapOf(
                    "Persuasión" to 0
                )
            )
        )
    ),
    ClassAbility(
        classType = "Pícaro",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Fuerza",
                abilityClass = mapOf(
                    "Atletismo" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Destreza",
                abilityClass = mapOf(
                    "Acrobacias" to 0,
                    "Juego de Manos" to 0,
                    "Sigilo" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Investigación" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Sabiduría",
                abilityClass = mapOf(
                    "Perspicacia" to 0,
                    "Percepción" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Carisma",
                abilityClass = mapOf(
                    "Engaño" to 0,
                    "Interpretación" to 0,
                    "Persuasión" to 0
                )
            )
        )
    ),
    ClassAbility(
        classType = "Chamán",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Arcanos" to 0,
                    "Historia" to 0,
                    "Naturaleza" to 0,
                    "Religión" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Sabiduría",
                abilityClass = mapOf(
                    "Perspicacia" to 0,
                    "Medicina" to 0,
                    "Supervivencia" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Carisma",
                abilityClass = mapOf(
                    "Persuasión" to 0
                )
            )
        )
    ),
    ClassAbility(
        classType = "Brujo",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Arcanos" to 0,
                    "Investigación" to 0,
                    "Historia" to 0,
                    "Naturaleza" to 0,
                    "Religión" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Carisma",
                abilityClass = mapOf(
                    "Engaño" to 0
                )
            )
        )
    ),
    ClassAbility(
        classType = "Guerrero",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Fuerza",
                abilityClass = mapOf(
                    "Atletismo" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Destreza",
                abilityClass = mapOf(
                    "Acrobacias" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Historia" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Sabiduría",
                abilityClass = mapOf(
                    "Perspicacia" to 0,
                    "Percepción" to 0,
                    "Supervivencia" to 0,
                    "Trato con Animales" to 0
                )
            )
        )
    ),
    ClassAbility(
        classType = "Caballero de la Muerte",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Fuerza",
                abilityClass = mapOf(
                    "Atletismo" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Religión" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Sabiduría",
                abilityClass = mapOf(
                    "Medicina" to 0,
                    "Perspicacia" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Carisma",
                abilityClass = mapOf(
                    "Engaño" to 0
                )
            )
        )
    ),
    ClassAbility(
        classType = "Monje",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Fuerza",
                abilityClass = mapOf(
                    "Atletismo" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Destreza",
                abilityClass = mapOf(
                    "Acrobacias" to 0,
                    "Sigilo" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Historia" to 0,
                    "Religión" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Sabiduría",
                abilityClass = mapOf(
                    "Perspicacia" to 0
                )
            )
        )
    ),
    ClassAbility(
        classType = "Cazador de Demonios",
        abilityContent = listOf(
            CharacterAbility(
                statOwner = "Fuerza",
                abilityClass = mapOf(
                    "Atletismo" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Destreza",
                abilityClass = mapOf(
                    "Acrobacias" to 0,
                    "Sigilo" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Inteligencia",
                abilityClass = mapOf(
                    "Arcanos" to 0,
                    "Investigación" to 0,
                    "Historia" to 0
                )
            ),
            CharacterAbility(
                statOwner = "Sabiduría",
                abilityClass = mapOf(
                    "Percepción" to 0,
                    "Perspicacia" to 0,
                    "Supervivencia" to 0
                )
            )
        )
    )
)