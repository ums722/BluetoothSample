package com.ums722.bluetoothsample.domain

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.ums722.bluetoothsample.domain.model.BluetoothDeviceEntity


@SuppressLint("MissingPermission")
fun BluetoothDevice.toEntity (): BluetoothDeviceEntity {
    return BluetoothDeviceEntity(
        name = name,
        address = address
    )
}