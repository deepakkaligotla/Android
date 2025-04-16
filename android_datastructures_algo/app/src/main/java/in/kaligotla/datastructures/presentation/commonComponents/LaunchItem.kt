package `in`.kaligotla.datastructures.presentation.commonComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.kaligotla.datastructures.data.domain.model.entities.Location

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchItem(location: Location, count: Int) {
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
                .size(width = 100.dp, height = 120.dp)
                .padding(1.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .testTag("Key"),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ID - " + count,
                    fontSize = 15.sp
                )
                Text(
                    text = "Pincode - " + location.pincode.toString(),
                    fontSize = 10.sp
                )
                Text(
                    text = location.area,
                    fontSize = 8.sp
                )
                Text(
                    text = location.city,
                    fontSize = 10.sp
                )
                Text(
                    text = location.district,
                    fontSize = 10.sp
                )
                Text(
                    text = location.state,
                    fontSize = 10.sp
                )
            }
        }
    }
}