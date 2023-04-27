package com.ums722.bluetoothsample.domain.model

typealias BluetoothDeviceEntity = BluetoothDevice

data class BluetoothDevice(
    val name: String?,
    val address: String
)
