package screens.player.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import domain.classList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassDropMenu(
    modifier: Modifier = Modifier,
    selectedClass: (String) -> Unit
) {
    var race by remember { mutableStateOf("") }
    var selectedRace by remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { newValue ->
            expanded = newValue
        }
    ) {
        OutlinedTextField(
            value = race,
            onValueChange = { race = it },
            modifier = modifier.menuAnchor(),
            label = { Text("Elige la clase") },
            singleLine = true,
            readOnly = true,
            maxLines = 1,
            interactionSource = remember { MutableInteractionSource() },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            scrollState = scrollState
        ) {
            classList.forEach {
                DropdownMenuItem(
                    onClick = {
                        selectedRace = it
                        race = selectedRace.toString()
                        selectedClass(race)

                        expanded = false
                    },
                    text = { Text(it) }
                )
            }
        }
        LaunchedEffect(expanded) {
            if (expanded) {
                scrollState.scrollTo(scrollState.maxValue)
            }
        }
    }
}