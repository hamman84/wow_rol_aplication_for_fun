package screens.player.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import domain.raceList
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun RaceDropMenu(
    modifier: Modifier = Modifier,
    selectedRace: (Pair<String, Map<String, Int>>) -> Unit
) {
    var race by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf<String?>(null) }
    var raceMap by remember { mutableStateOf<Pair<String, Map<String, Int>>?>(null) }
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
            label = { Text("Elige la raza") },
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
            raceList.forEach {
                DropdownMenuItem(
                    onClick = {
                        selectedType = it.raceName
                        race = selectedType.toString()

                        raceMap = it.raceName to it.racialStats
                        selectedRace(raceMap!!)

                        expanded = false
                    },
                    text = { Text(it.raceName) },
                    leadingIcon = { Image(
                        painter = painterResource(it.raceImage),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(60.dp).clip(CircleShape)
                    ) },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
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