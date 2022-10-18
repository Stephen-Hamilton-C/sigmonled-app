package app.shamilton.common

import com.welie.blessed.BluetoothCentralManager
import com.welie.blessed.BluetoothCentralManagerCallback
import com.welie.blessed.BluetoothPeripheral
import com.welie.blessed.ScanResult

actual fun getPlatformName(): String {
    return "Desktop"
}

private var testingBluetooth = false;
actual fun testBluetooth() {
    if(testingBluetooth) return
    testingBluetooth = true
    val peripherals = mutableSetOf<String>()
    val bluetoothCentralManagerCallback = object : BluetoothCentralManagerCallback() {
        override fun onDiscoveredPeripheral(peripheral: BluetoothPeripheral, scanResult: ScanResult) {
            if(peripherals.add(peripheral.address)) {
                println("Found peripheral: ${peripheral.address}, Total of ${peripherals.size} peripherals found")
            }
        }
    }

    val blueMan = BluetoothCentralManager(bluetoothCentralManagerCallback)
    blueMan.scanForPeripherals()
}