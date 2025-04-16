package `in`.kaligotla.myanimations

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import `in`.kaligotla.myanimations.ui.theme.MyAnimationsTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAnimationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                    MainCompose()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var toggled by remember { mutableIntStateOf(0) }
    val interactionSource = remember { MutableInteractionSource() }
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = LocalIndication.current,
                interactionSource = interactionSource
            ) {
                if (toggled < 4) toggled += 1 else toggled = 0
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val offsetTarget = if (toggled==1) { IntOffset(1000, -2500) }
        else if(toggled==2) { IntOffset(1000, 2500) }
        else if(toggled==3) { IntOffset(-1000, 2500) }
        else if(toggled==4) { IntOffset(-1000, -2500) }
        else { IntOffset.Zero }
        val offset = animateIntOffsetAsState(targetValue = offsetTarget, label = "offset")

        Box() {
            Text(
                text = if(scale<3.5f) "❤️" else "💔️",
                modifier = Modifier
                    .layout { measurable, constraints ->
                        val offsetValue = if (isLookingAhead) offsetTarget else offset.value
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width + offsetValue.x, placeable.height + offsetValue.y) {
                            placeable.placeRelative(offsetValue)
                        }
                    }
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = TransformOrigin.Center
                    }
                    .align(Alignment.Center),
                style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAnimationsTheme {
        Greeting()
    }
}