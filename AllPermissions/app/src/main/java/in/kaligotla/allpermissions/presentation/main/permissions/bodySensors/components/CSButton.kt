package `in`.kaligotla.allpermissions.presentation.main.permissions.bodySensors.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

enum class CSButtonPosition { Start, End }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CSButton(
    text: String,
    icon: Int,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    position: CSButtonPosition,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    contentPadding: PaddingValues = when (position) {
        CSButtonPosition.Start -> PaddingValues(start = 4.dp, end = 16.dp)
        CSButtonPosition.End -> PaddingValues(start = 16.dp, end = 8.dp)
    }
) {
    Surface(
        modifier = modifier
            .graphicsLayer {
                this.clip = true
                this.shape = shape
            }
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = if (enabled) LocalIndication.current else null,
                onClick = { if (enabled) onClick() },
                onLongClick = { if (enabled) onLongClick() }
            ),
        color = animateColorAsState(
            targetValue = MaterialTheme.colorScheme.primary.copy(alpha = if (enabled) 1f else 0.5f)
        ).value,
        contentColor = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = ButtonDefaults.MinWidth,
                    minHeight = ButtonDefaults.MinHeight
                )
                .padding(4.dp)
                .padding(contentPadding),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (position == CSButtonPosition.Start) {
                Image(painter = painterResource(id = icon), contentDescription = "left arrow")
            }
            Column(
                horizontalAlignment = when (position) {
                    CSButtonPosition.Start -> Alignment.End
                    CSButtonPosition.End -> Alignment.Start
                }
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Hold to skip",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Normal
                )
            }
            if (position == CSButtonPosition.End) {
                Image(painter = painterResource(id = icon), contentDescription = "right arrow")
            }
        }
    }
}
