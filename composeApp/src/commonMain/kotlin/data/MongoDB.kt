package data


import domain.BasicStats
import domain.Character
import domain.Inventory
import domain.ItemLooted
import domain.Player
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.ext.profileAsBsonDocument
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId
import util.Constants.APP_ID

object MongoDB : MongoRepository {
    val app = App.create(APP_ID)
    val user = app.currentUser
    private lateinit var realm: Realm

    init {
        configureRealm()
    }

    override fun configureRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(
                user = user,
                schema = setOf(
                    Player::class,
                    Character::class,
                    Inventory::class,
                    ItemLooted::class,
                    BasicStats::class
                )
            )
                .initialSubscriptions { realm ->
                    add(query = realm.query<Player>(query = "owner_id == $0", user.id))
                }
                .schemaVersion(0)
                .build()

            realm = Realm.open(config)

        }
    }

    override suspend fun loginPlayer(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val emailPasswordCredentials = Credentials.emailPassword(email, password)
            try {
                app.login(emailPasswordCredentials)

            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    override suspend fun upsertPlayer(userId: String, newCharacter: Character) {
        if (user != null) {
            realm.write {
                val existingPlayer = query<Player>("owner_id == $0", userId).first().find()
                if (existingPlayer != null) {
                    try {
                        existingPlayer.characters.add(newCharacter)
                        copyToRealm(existingPlayer, UpdatePolicy.ALL)
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }else{
                    try {
                        val newPlayer = Player().apply {
                            _id = BsonObjectId()
                            userName = user.profileAsBsonDocument()["email"]?.asString()?.value ?: "Unknown"
                            owner_id = user.id
                            characters.add(newCharacter)
                        }
                        copyToRealm(newPlayer, UpdatePolicy.ALL)
                    }catch (e:Exception){
                        println(e.message)
                    }
                }
            }
        }
    }

    override suspend fun getPlayerById(id: String): Player? {
        return if (user != null) {
            realm.query<Player>("owner_id == $0", id).find().firstOrNull()
        } else {
            null
        }

    }

    override suspend fun getPLayerName(): String? {
        return if (user != null) {
            user.profileAsBsonDocument()["email"]?.asString()?.value
        } else {
            null
        }
    }

    override suspend fun addPlayer(newUser: Player) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(newUser.apply { owner_id = user.id })
                } catch (e: Exception) {
                    println("Error adding player: ${e.message}")
                }
            }
        }
    }

    override fun getPlayers(): Flow<List<Player>> {
        return realm.query<Player>().asFlow().map { it.list }
    }

    override suspend fun logoutPlayer(selectedUser: io.realm.kotlin.mongodb.User) {
        user?.logOut()
    }

    override suspend fun deletePlayer(id: ObjectId) {
        realm.write {
            try {
                val user = query<Player>("_id == $0", id).first().find()
                user?.let { delete(it) }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    override fun getCharacters(): Flow<List<Character>> {
        return if (user != null) {
            val query = realm.query<Character>()
            val flow = query.asFlow()
            flow.map { it.list }
        } else {
            flowOf(emptyList())
        }
    }

    override suspend fun addCharacter(playerName: String, newCharacter: Character) {

        try {
            realm.write {
                val player = query<Player>("userName == $0", playerName).first().find()

                player?.characters?.add(newCharacter.apply { owner = playerName })

            }
        } catch (e: Exception) {
            // Handle the exception appropriately, e.g., log the error, display a message to the user
            println("Error adding character: ${e.message}")
        }

    }

    override suspend fun updateCharacter(playerId: String, updatedCharacter: Character) {
        try {
            realm.write {
                val player = query<Player>("_id == $0", playerId).first().find()
                if (player != null) {
                    val existingCharacterIndex = player.characters.indexOfFirst { it.name == updatedCharacter.name }
                    if (existingCharacterIndex != -1) {
                        player.characters[existingCharacterIndex] = updatedCharacter
                    } else {
                        println("Character with name ${updatedCharacter.name} not found")
                    }
                } else {
                    println("Player with ID $playerId not found")
                }
            }
        } catch (e: Exception) {
            // Handle the exception appropriately
            println("Error updating character: ${e.message}")
        }
    }

    override suspend fun deleteCharacter(playerId: String, characterName: String) {
        try {
            realm.write {
                val player = query<Player>("_id == $0", playerId).first().find()
                if (player != null) {
                    val characterToDelete = player.characters.find { it.name == characterName }
                    if (characterToDelete != null) {
                        player.characters.remove(characterToDelete)
                    } else {
                        println("Character with name $characterName not found")
                    }
                } else {
                    println("Player with ID $playerId not found")
                }
            }
        } catch (e: Exception) {
            // Handle the exception appropriately
            println("Error deleting character: ${e.message}")
        }
    }

}