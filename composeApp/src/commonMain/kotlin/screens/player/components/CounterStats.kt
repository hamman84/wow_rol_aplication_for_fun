package screens.player.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CounterStats(
    modifier: Modifier = Modifier,
    abilityName: String,
    initialPoints: Int,
    onPointsChange: (Int) -> Unit,
    activeAdd: Boolean
) {
    val points = remember { mutableStateOf(initialPoints) }

    Row(
        modifier = modifier.fillMaxWidth().padding(start = 45.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Box(modifier = Modifier.weight(.5f)){
            Text(
                text = "$abilityName:",
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                shape = CircleShape,
                onClick = {
                    if (points.value > 0){
                        points.value --
                        onPointsChange(points.value)
                    }
                }
            ){ Text("-") }
            Spacer(modifier = Modifier.width(18.dp))
            Text(
                text = "${points.value}",
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(18.dp))
            Button(
                shape = CircleShape,
                enabled = activeAdd,
                onClick = {
                    if (points.value < 16) {
                        points.value ++
                        onPointsChange(points.value)
                    }
                }
            ){ Text("+") }
        }
    }
}