package com.ums722.bluetoothsample.di

import android.content.Context
import com.ums722.bluetoothsample.data.AndroidBluetoothController
import com.ums722.bluetoothsample.domain.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context): BluetoothController = AndroidBluetoothController(context)
}