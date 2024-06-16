package data

import domain.Character
import domain.Player
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface MongoRepository {
    fun configureRealm()
    fun getPlayers(): Flow<List<Player>>
    fun getCharacters(): Flow<List<Character>>
    suspend fun loginPlayer(email: String, password: String)
    suspend fun getPlayerById(id: String): Player?
    suspend fun getPLayerName(): String?
    suspend fun addPlayer(newUser: Player)
    suspend fun upsertPlayer(userId: String, newCharacter: Character)
    suspend fun logoutPlayer(selectedUser: User)
    suspend fun deletePlayer(id: ObjectId)
    suspend fun addCharacter(playerName: String, newCharacter: Character)
    suspend fun updateCharacter(playerId: String, updatedCharacter: Character)
    suspend fun deleteCharacter(playerId: String, characterName: String)

}