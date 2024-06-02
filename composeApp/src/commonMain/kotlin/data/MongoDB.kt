package data


import domain.BasicStats
import domain.Character
import domain.Inventory
import domain.ItemLooted
import domain.User
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import util.Constants.APP_ID

object MongoDB : MongoRepository {
    private val app = App.create(APP_ID)
    private val user = app.currentUser
    private lateinit var realm: Realm

    init {
        configureRealm()
    }

    override fun configureRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(
                user = user,
                schema = setOf(
                    User::class,
                    Character::class,
                    Inventory::class,
                    ItemLooted::class,
                    BasicStats::class
                )
            )
                .initialSubscriptions { realm ->
                    add(query = realm.query<User>(query = "owner_id == $0", user.id))
                }
                .schemaVersion(0)
                .build()

            realm = Realm.open(config)

        }
    }

    override suspend fun loginUser(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val emailPasswordCredentials = Credentials.emailPassword(email, password)
            try {
                app.login(emailPasswordCredentials)
            }catch (e: Exception){
                println(e.message)
            }

        }
    }

    override suspend fun getUserById(id: String): Boolean {
        val result = user?.let { realm.query<User>("owner_id == $0", it.id).find().first() }
        return result != null
    }

    override suspend fun addUser(newUser: User) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(newUser.apply { owner_id = user.id })
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }
    }

    override fun getUsers(): Flow<List<User>> {
        return realm.query<User>().asFlow().map { it.list }
    }

    override suspend fun updateUser(selectedUser: User) {
        TODO("Not yet implemented")
    }

    override suspend fun logoutUser(selectedUser: io.realm.kotlin.mongodb.User) {
        user?.logOut()
    }

    override suspend fun deleteUser(id: ObjectId) {
        realm.write {
            try {
                val user = query<User>("_id == $0", id).first().find()
                user?.let { delete(it) }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    override suspend fun addCharacter(userChar: Character) {
        TODO("Not yet implemented")
    }


    override fun getCharacters(): Flow<List<Character>> {
        return if (user != null) {
            val query = realm.query<Character>()
            val flow = query.asFlow()
            flow.map { it.list }
        } else{ flowOf(emptyList()) }
    }



    override suspend fun updateCharacter(userChar: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCharacter(userChar: Character) {
        TODO("Not yet implemented")
    }

}