package screens.player.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import domain.classAbiltyList
import util.getAbilitiesMaxCount

@Composable
fun AbilityClassCheckBox(
    modifier: Modifier = Modifier,
    onAbilitySelected: (String, Boolean) -> Unit,
    abilityStates: Map<String, Int>,
    selectedClass: String
) {
    val abilitiesGrouped = classAbiltyList
        .filter { it.classType == selectedClass }
        .flatMap { it.abilityContent }
        .groupBy { it.statOwner }

    Column(modifier = modifier) {
        abilitiesGrouped.forEach { (statOwner, abilities) ->
            Text(
                text = statOwner,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge.copy(
                    textDecoration = TextDecoration.Underline
                )
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                items(abilities.flatMap { it.abilityClass.keys }) { stat ->
                    val isSelected = abilityStates[stat] ?: 0
                    val maxCount = getAbilitiesMaxCount(selectedClass)
                    val isEnabled =
                        isSelected > 0 || abilityStates.count { it.value > 0 } < maxCount

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isSelected > 0,
                            onCheckedChange = { isChecked ->
                                onAbilitySelected(stat, isChecked)
                            },
                            enabled = isEnabled
                        )
                        Text(
                            text = stat,
                            modifier = Modifier.padding(start = 4.dp),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}