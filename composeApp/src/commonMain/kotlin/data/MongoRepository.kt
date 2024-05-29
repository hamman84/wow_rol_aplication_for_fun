package data

import domain.Character
import domain.User
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface MongoRepository {
    fun configureRealm()
    fun getUsers(): Flow<List<User>>
    fun getCharacters(): Flow<List<Character>>
    suspend fun loginUser(email: String, password: String)
    suspend fun getUserById(id: String): Boolean
    suspend fun addUser(newUser: User)
    suspend fun updateUser(selectedUser: User)
    suspend fun logoutUser(selectedUser: io.realm.kotlin.mongodb.User)
    suspend fun deleteUser(id: ObjectId)
    suspend fun addCharacter(userChar: Character)
    suspend fun updateCharacter(userChar: Character)
    suspend fun deleteCharacter(userChar: Character)

}