package com.example.hashim.ezpresenter.Factories;

import android.bluetooth.BluetoothAdapter;

import com.example.hashim.ezpresenter.Interfaces.ISelectPresentationPresenter;
import com.example.hashim.ezpresenter.Interfaces.ISocket;
import com.example.hashim.ezpresenter.Interfaces.ISocketFactory;
import com.example.hashim.ezpresenter.Models.CustomBluetoothSocket;

/**
 * Created by Hashim on 11.7.2016 г..
 */
public class SocketFactory implements ISocketFactory{
    @Override
    public ISocket getBluetoothSocket(BluetoothAdapter bluetoothAdapter, String deviceNameToConnect) {

        return new CustomBluetoothSocket(bluetoothAdapter, deviceNameToConnect);
    }
}
