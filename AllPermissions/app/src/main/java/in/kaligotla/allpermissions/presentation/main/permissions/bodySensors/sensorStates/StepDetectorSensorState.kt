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
class StepDetectorSensorState internal constructor(
    val stepCount: Float = 0f,
    val isAvailable: Boolean = false,
    val accuracy: Int = 0,
    private val startListeningEvents: (() -> Unit)? = null,
    private val stopListeningEvents: (() -> Unit)? = null,
) : SensorStateListener {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StepDetectorSensorState) return false

        if (stepCount != other.stepCount) return false
        if (isAvailable != other.isAvailable) return false
        if (accuracy != other.accuracy) return false
        if (startListeningEvents != other.startListeningEvents) return false
        if (stopListeningEvents != other.stopListeningEvents) return false

        return true
    }

    override fun hashCode(): Int {
        var result = stepCount.hashCode()
        result = 31 * result + isAvailable.hashCode()
        result = 31 * result + accuracy.hashCode()
        result = 31 * result + startListeningEvents.hashCode()
        result = 31 * result + stopListeningEvents.hashCode()
        return result
    }

    override fun toString(): String {
        return "StepDetectorSensorState(stepCount=$stepCount isAvailable=$isAvailable," +
                " accuracy=$accuracy)"
    }

    override fun startListening() {
        startListeningEvents?.invoke()
    }

    override fun stopListening() {
        stopListeningEvents?.invoke()
    }
}

@Composable
fun rememberStepDetectorSensorState(
    autoStart: Boolean = true,
    sensorDelay: SensorDelay = SensorDelay.Normal,
    onError: (throwable: Throwable) -> Unit = {},
): StepDetectorSensorState {
    val sensorState = rememberSensorState(
        sensorType = SensorType.StepDetector,
        sensorDelay = sensorDelay,
        autoStart = autoStart,
        onError = onError
    )
    val stepDetectorSensorState = remember {
        mutableStateOf(
            StepDetectorSensorState(
                startListeningEvents = sensorState::startListening,
                stopListeningEvents = sensorState::stopListening
            )
        )
    }

    LaunchedEffect(
        key1 = sensorState,
        block = {
            val sensorStateValues = sensorState.data
            if (sensorStateValues.isNotEmpty()) {
                stepDetectorSensorState.value = StepDetectorSensorState(
                    stepCount = sensorStateValues[0],
                    isAvailable = sensorState.isAvailable,
                    accuracy = sensorState.accuracy,
                    startListeningEvents = sensorState::startListening,
                    stopListeningEvents = sensorState::stopListening
                )
            }
        }
    )

    return stepDetectorSensorState.value
}
