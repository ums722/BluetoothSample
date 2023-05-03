package com.ums722.bluetoothsample.domain

sealed interface ConnectionResult{
    object ConnectionEstablished: ConnectionResult
    data class Error(val message: String): ConnectionResult

}