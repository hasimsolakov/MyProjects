package com.example.hashim.ezpresenter.Interfaces;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by Hashim on 11.7.2016 г..
 */
public interface ISocketFactory {
    ISocket getBluetoothSocket(BluetoothAdapter bluetoothAdapter, String deviceNameToConnect);
}
