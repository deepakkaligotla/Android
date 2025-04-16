package `in`.kaligotla.datastructures.presentation.commonComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DisplayListItem(item1: Any?, item2: Any?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .padding(1.dp),
        ) {
            Row(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$item1\n Value:\n$item2",
                        modifier = Modifier.padding(1.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}