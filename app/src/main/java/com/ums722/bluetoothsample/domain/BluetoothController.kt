package com.ums722.bluetoothsample.domain

import com.ums722.bluetoothsample.domain.model.BluetoothDevice
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val scannedDevice: StateFlow<List<BluetoothDevice>>
    val pairedDevices: StateFlow<List<BluetoothDevice>>

    fun startDiscovery()
    fun stopDiscovery()

    fun release()

}