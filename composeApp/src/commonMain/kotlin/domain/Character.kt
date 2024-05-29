package domain

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.mongodb.kbson.BsonObjectId


class User : RealmObject {
    @PrimaryKey
    var _id: BsonObjectId = BsonObjectId()
    var userName: String = ""
    var userPass: String = ""
    var characters: RealmList<Character> = realmListOf()
    var owner_id: String = ""
}

class Character : EmbeddedRealmObject {
    var name: String = ""
    var level: Int = 0
    var race: String = ""
    var luckScore: Int = 0
    var classType: String = ""
    var experience: Int = 0
    var stats: BasicStats? = null
    var inventory: ItemLoot? = null
    var equipment: ItemLooted? = null
    var owner: String = ""
}

val classList = listOf(
    "Paladin",
    "Guerrero",
    "Sacerdote",
    "Pícaro",
    "Cazador",
    "Druida",
    "Cazador de Demonios",
    "Chamán",
    "Brujo",
    "Mago",
    "Monje",
    "Caballero de la Muerte",
    "Evocador"
)

data class Race @OptIn(ExperimentalResourceApi::class) constructor(
    val raceName: String,
    val raceImage: DrawableResource,
    val racialStats: Map<String, Int>,
)

class BasicStats : EmbeddedRealmObject {
    var strength: Int = 0
    var dexterity: Int = 0
    var constitution: Int = 0
    var intelligence: Int = 0
    var charisma: Int = 0
    var wisdom: Int = 0
}

class ItemLoot : EmbeddedRealmObject {
    var gold: Int = 0
    var silver: Int = 0
    var inventory: RealmList<ItemLooted> = realmListOf()
}

class ItemLooted : EmbeddedRealmObject {
    var helmet: String = ""
    var chest: String = ""
    var legs: String = ""
    var shoes: String = ""
    var ring: String = ""
    var neck: String = ""
    var weapon: String = ""
}