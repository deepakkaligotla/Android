fun main() {
    val light: SmartDevice = SmartLight("Living Room Light")
    val thermostat: SmartDevice = SmartThermostat("Hall Thermostat")
    println(light.deviceInfo())
    println(thermostat.deviceInfo())

    light.turnOn()
    println("Light status: ${light.getStatus()}")

    if (light is VoiceControllable) {
        light.onVoiceCommand("increase brightness")
    }
    if (light is Schedulable) {
        light.schedule("8:00 PM")
    }

    when(thermostat) {
        is VoiceControllable -> thermostat.onVoiceCommand("increase temp")
    }

}

abstract class SmartDevice(
    private var name: String,
    private var isOn: Boolean = false
): VoiceControllable {
    fun turnOn() {
        isOn = true
        println("$name is turned ON.")
    }
    fun turnOff() {
        isOn = false
        println("$name is turned OFF.")
    }
    fun getStatus(): Boolean = isOn
    abstract fun deviceInfo(): String
}

class SmartLight(name: String, var brightness: Int = 50) : SmartDevice(name), Schedulable {
    override fun onVoiceCommand(command: String) {
        when (command.lowercase()) {
            "turn on light" -> turnOn()
            "turn off light" -> turnOff()
            "increase brightness" -> {
                brightness += 10
                println("Brightness increased to $brightness%")
            }
            else -> println("Unknown command for SmartLight")
        }
    }
    override fun schedule(time: String) {
        println("SmartLight scheduled to turn on at $time")
    }
    override fun deviceInfo(): String {
        return "SmartLight with brightness $brightness%"
    }
}

class SmartThermostat(name: String, private var temperature: Double = 22.0) : SmartDevice(name) {
    fun setTemperature(temp: Double) {
        temperature = temp
        println("Temperature set to $temperature°C")
    }
    override fun onVoiceCommand(command: String) {
        when (command.lowercase()) {
            "increase temp" -> setTemperature(temperature + 1)
            "decrease temp" -> setTemperature(temperature - 1)
            else -> println("Unknown command for SmartThermostat")
        }
    }
    override fun deviceInfo(): String {
        return "SmartThermostat set to $temperature°C"
    }
}

interface VoiceControllable {
    fun onVoiceCommand(command: String)
}

interface Schedulable {
    fun schedule(time: String)
}