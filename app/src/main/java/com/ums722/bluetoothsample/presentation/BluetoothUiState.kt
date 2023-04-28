package com.ums722.bluetoothsample.presentation

import android.bluetooth.BluetoothDevice

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices : List<BluetoothDevice> = emptyList()

)
