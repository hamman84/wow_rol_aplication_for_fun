package screens.player.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import screens.login.LoginScreen
import screens.player.components.CharacterTag
import screens.player.models.HomeViewModel


class HomeScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val viewModel = rememberScreenModel { HomeViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val characters = viewModel.data.value
        val scope = rememberCoroutineScope()


        Scaffold(
            floatingActionButton = {
                LargeFloatingActionButton(
                    onClick = {navigator.push(CreateCharacterScreen())}
                ){
                    Icon(Icons.Filled.Add, contentDescription = null)
                }
            },
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Personajes de: ${viewModel.activeUser?.id ?: "Usuario"}") },
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    viewModel.logoutUser { navigator.push(LoginScreen()) }
                                }
                            }
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null)
                        }
                    }
                )
            }
        ) {
            if (characters.isEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize().padding(it),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "No tienes personajes, créalos!!")
                }
            }else{
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(it),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    items(characters){pnj ->
                        CharacterTag(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            characters = pnj,
                            onClickTag = {navigator.push(CharacterScreen(pnj))}
                        )
                    }
                }
            }
        }
    }
}