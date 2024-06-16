package screens.player.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import domain.abilityList

@Composable
fun BackgroundCheckBoxes(
    modifier: Modifier = Modifier,
    onAbilitySelected: (String, Boolean) -> Unit,
    abilityStates: Map<String, Int>,
    disabledAbilities: Set<String>
) {

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        abilityList.forEach { ability ->
            var isExpanded by remember { mutableStateOf(false) }

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isExpanded = !isExpanded }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = ability.statOwner,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            textDecoration = TextDecoration.Underline
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Contraer" else "Expandir",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                AnimatedVisibility(visible = isExpanded) {
                    Column {
                        ability.abilityClass.keys.forEach { stat ->
                            val isSelected = abilityStates[stat] ?: 0
                            val maxCount = disabledAbilities.size + 2
                            val isEnabled = (!disabledAbilities.contains(stat)) &&
                                    (isSelected > 0 || abilityStates.count { it.value > 0 } < maxCount)

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isSelected > 0,
                                    onCheckedChange = { isChecked ->
                                        onAbilitySelected(stat, isChecked)
                                        println(maxCount)
                                        println(isSelected)
                                    },
                                    enabled = isEnabled
                                )
                                Text(
                                    text = stat,
                                    modifier = Modifier.padding(start = 16.dp),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}