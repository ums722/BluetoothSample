package com.ums722.bluetoothsample.presentation

import com.ums722.bluetoothsample.domain.model.BluetoothDevice


data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices : List<BluetoothDevice> = emptyList()

)
