package `in`.kaligotla.allpermissions.presentation.main.permissions.bodySensors.sensorStates

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import `in`.kaligotla.allpermissions.presentation.main.permissions.bodySensors.common.SensorDelay
import `in`.kaligotla.allpermissions.presentation.main.permissions.bodySensors.common.SensorStateListener
import `in`.kaligotla.allpermissions.presentation.main.permissions.bodySensors.common.SensorType
import `in`.kaligotla.allpermissions.presentation.main.permissions.bodySensors.common.rememberSensorState

@Immutable
class GameRotationVectorSensorState internal constructor(
    val vectorX: Float = 0f,
    val vectorY: Float = 0f,
    val vectorZ: Float = 0f,
    val isAvailable: Boolean = false,
    val accuracy: Int = 0,
    private val startListeningEvents: (() -> Unit)? = null,
    private val stopListeningEvents: (() -> Unit)? = null,
) : SensorStateListener {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other as GameRotationVectorSensorState

        if (vectorX != other.vectorX) return false
        if (vectorY != other.vectorY) return false
        if (vectorZ != other.vectorZ) return false
        if (isAvailable != other.isAvailable) return false
        if (accuracy != other.accuracy) return false
        if (startListeningEvents != other.startListeningEvents) return false
        if (stopListeningEvents != other.stopListeningEvents) return false
        return true
    }

    override fun hashCode(): Int {
        var result = vectorX.hashCode()
        result = 31 * result + vectorY.hashCode()
        result = 31 * result + vectorZ.hashCode()
        result = 31 * result + isAvailable.hashCode()
        result = 31 * result + accuracy.hashCode()
        result = 31 * result + startListeningEvents.hashCode()
        result = 31 * result + stopListeningEvents.hashCode()
        return result
    }

    override fun toString(): String {
        return "GameRotationVectorSensorState(vectorX=$vectorX, " +
                "vectorY=$vectorY, vectorZ=$vectorZ, isAvailable=$isAvailable, " +
                "accuracy=$accuracy)"
    }

    override fun startListening() {
        startListeningEvents?.invoke()
    }

    override fun stopListening() {
        stopListeningEvents?.invoke()
    }
}

@Composable
fun rememberGameRotationVectorSensorState(
    autoStart: Boolean = true,
    sensorDelay: SensorDelay = SensorDelay.Game,
    onError: (throwable: Throwable) -> Unit = {},
): GameRotationVectorSensorState {
    val sensorState = rememberSensorState(
        sensorType = SensorType.GameRotationVector,
        sensorDelay = sensorDelay,
        autoStart = autoStart,
        onError = onError
    )

    val gameRotationVectorSensorState = remember {
        mutableStateOf(
            GameRotationVectorSensorState(
                startListeningEvents = sensorState::startListening,
                stopListeningEvents = sensorState::stopListening
            )
        )
    }

    LaunchedEffect(key1 = sensorState, block = {
        val sensorStateValues = sensorState.data
        if (sensorStateValues.isNotEmpty()) {
            gameRotationVectorSensorState.value = GameRotationVectorSensorState(
                vectorX = sensorStateValues[0],
                vectorY = sensorStateValues[1],
                vectorZ = sensorStateValues[2],
                isAvailable = sensorState.isAvailable,
                accuracy = sensorState.accuracy,
                startListeningEvents = sensorState::startListening,
                stopListeningEvents = sensorState::stopListening
            )
        }
    })

    return gameRotationVectorSensorState.value
}
