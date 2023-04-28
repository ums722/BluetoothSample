package com.ums722.bluetoothsample.data

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import com.ums722.bluetoothsample.domain.BluetoothController
import com.ums722.bluetoothsample.domain.model.BluetoothDeviceEntity
import com.ums722.bluetoothsample.domain.toEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@SuppressLint("MissingPermission")
class AndroidBluetoothController(private val context: Context) : BluetoothController {

    private val bluetoothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }

    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val _scannedDevices = MutableStateFlow<List<BluetoothDeviceEntity>>(emptyList())
    override val scannedDevices: StateFlow<List<BluetoothDeviceEntity>>
        get() = _scannedDevices.asStateFlow()

    private val _pairedDevices = MutableStateFlow<List<BluetoothDeviceEntity>>(emptyList())
    override val pairedDevices: StateFlow<List<BluetoothDeviceEntity>>
        get() = _pairedDevices.asStateFlow()

    private val foundDeviceReceiver = FoundDeviceReceiver { device ->
        _scannedDevices.update {devices ->
            val newDevice = device.toEntity()
            if (newDevice in devices) devices else devices + newDevice
        }
    }

    init {
        updatePairedDevice()
    }

    override fun startDiscovery() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
            return
        }
        context.registerReceiver(
            foundDeviceReceiver,
            IntentFilter(BluetoothDevice.ACTION_FOUND)
        )
        updatePairedDevice()
        bluetoothAdapter?.startDiscovery()
    }

    override fun stopDiscovery() {
      if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)){
          return
      }
        bluetoothAdapter?.cancelDiscovery()
    }

    override fun release() {
      context.unregisterReceiver(foundDeviceReceiver)

    }

    private fun updatePairedDevice() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
            return
        }

        bluetoothAdapter?.bondedDevices?.map {
            it.toEntity()
        }?.also { device ->
            _pairedDevices.update { device }
        }
    }

    private fun hasPermission(permission: String): Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}