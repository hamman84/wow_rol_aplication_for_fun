package screens.player.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import domain.Character
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import util.getRaceImage

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CharacterTag(
    modifier: Modifier = Modifier,
    onClickTag: () -> Unit,
    characters: Character,
) {
    val raceImage = getRaceImage(characters.race)
    ElevatedCard(
        modifier = modifier
            .padding(10.dp)
            .clickable { onClickTag() },
        shape = ShapeDefaults.Small
    ){
        Row(
            modifier = modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(raceImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = characters.name, style = MaterialTheme.typography.titleLarge)
                Text(text = "Nivel: ${characters.level}")
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "Clase: ${characters.classType}")
                Text(text = "Exp. ${characters.experience}pts")
                LinearProgressIndicator(
                    progress = { characters.experience.toFloat() / 150 },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}