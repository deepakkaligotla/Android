package `in`.kaligotla.allpermissions.presentation.main.permissions.bodySensors.sensorStates

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import `in`.kaligotla.allpermissions.presentation.main.permissions.bodySensors.common.SensorDelay
import `in`.kaligotla.allpermissions.presentation.main.permissions.bodySensors.common.SensorStateListener
import `in`.kaligotla.allpermissions.presentation.main.permissions.bodySensors.common.SensorType
import `in`.kaligotla.allpermissions.presentation.main.permissions.bodySensors.common.rememberSensorState

@RequiresApi(Build.VERSION_CODES.N)
@Immutable
class StationaryDetectSensorState internal constructor(
    val isDeviceStationary: Boolean = false,
    val isAvailable: Boolean = false,
    val accuracy: Int = 0,
    private val startListeningEvents: (() -> Unit)? = null,
    private val stopListeningEvents: (() -> Unit)? = null,
) : SensorStateListener {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StationaryDetectSensorState) return false

        if (isDeviceStationary != other.isDeviceStationary) return false
        if (isAvailable != other.isAvailable) return false
        if (accuracy != other.accuracy) return false
        if (startListeningEvents != other.startListeningEvents) return false
        if (stopListeningEvents != other.stopListeningEvents) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isDeviceStationary.hashCode()
        result = 31 * result + isAvailable.hashCode()
        result = 31 * result + accuracy.hashCode()
        result = 31 * result + startListeningEvents.hashCode()
        result = 31 * result + stopListeningEvents.hashCode()
        return result
    }

    override fun toString(): String {
        return "StationaryDetectSensorState(isDeviceStationary=$isDeviceStationary," +
                " isAvailable=$isAvailable, accuracy=$accuracy)"
    }

    override fun startListening() {
        startListeningEvents?.invoke()
    }

    override fun stopListening() {
        stopListeningEvents?.invoke()
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun rememberStationaryDetectSensorState(
    autoStart: Boolean = true,
    sensorDelay: SensorDelay = SensorDelay.Normal,
    onError: (throwable: Throwable) -> Unit = {},
): StationaryDetectSensorState {
    val sensorState = rememberSensorState(
        sensorType = SensorType.StationaryDetect,
        sensorDelay = sensorDelay,
        autoStart = autoStart,
        onError = onError
    )
    val confidenceSensorState = remember {
        mutableStateOf(
            StationaryDetectSensorState(
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
                confidenceSensorState.value = StationaryDetectSensorState(
                    isDeviceStationary = sensorStateValues[0] == 1f,
                    isAvailable = sensorState.isAvailable,
                    accuracy = sensorState.accuracy,
                    startListeningEvents = sensorState::startListening,
                    stopListeningEvents = sensorState::stopListening
                )
            }
        }
    )

    return confidenceSensorState.value
}
