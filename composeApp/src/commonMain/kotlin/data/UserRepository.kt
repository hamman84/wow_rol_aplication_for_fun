package data

import domain.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object UserRepository {
    private val _activeUserFlow = MutableStateFlow<Player?>(null) // Use Player?
    val activeUserFlow: StateFlow<Player?> = _activeUserFlow.asStateFlow()

    var activeUser: Player? = null // Use Player?
        set(value) {
            field = value
            _activeUserFlow.value = value
        }
}