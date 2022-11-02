package app.shamilton.sigmonled.ui.devices

import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import app.shamilton.sigmonled.core.ArduinoCommander
import com.badoo.reaktive.observable.subscribe

private val devMan = ArduinoCommander.deviceManager
private val discoveredDevices = mutableListOf<BluetoothDevice>()

private fun scanClicked() {
    devMan.scan()
}

@Composable
fun DeviceList() {
    var scanButtonEnabled by remember { mutableStateOf(true) }
    devMan.onScanningStarted.subscribe { scanButtonEnabled = false }
    devMan.onScanningStopped.subscribe { scanButtonEnabled = true }
    devMan.onDeviceFound.subscribe { device ->
        discoveredDevices.add(device)
    }

    Column() {
        // Scan button
        Button(onClick = ::scanClicked, enabled = scanButtonEnabled) {
            Text("Scan")
        }

        // List
        for(device in discoveredDevices) {
            DeviceButton(device)
        }
    }
}

@Preview
@Composable
fun Preview() {
    DeviceList()
}
