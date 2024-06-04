package screens.player.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import domain.abilityList

@Composable
fun AbilityRadioButtons(
    modifier: Modifier = Modifier,
    onAbilitySelected: (String, Boolean) -> Unit,
    abilityStates: Map<String, Int>,
    selectedClass: String
) {
    Column(modifier = modifier){
        abilityList.forEach { ability ->
            Column{
                Text(
                    text = ability.statOwner,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Column {
                    ability.abilityClass.forEach{ (stat) ->
                        val isSelected = abilityStates[stat] ?:0
                        val maxCount = if (selectedClass == "Pícaro") 3 else 2
                        val isEnabled = isSelected > 0 || abilityStates.count { it.value > 0 } < maxCount
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){

                            Checkbox(
                                checked = isSelected > 0,
                                onCheckedChange = {isChecked ->
                                    onAbilitySelected(stat, isChecked)
                                },
                                enabled = isEnabled
                            )
                            Text(
                                text = stat,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}