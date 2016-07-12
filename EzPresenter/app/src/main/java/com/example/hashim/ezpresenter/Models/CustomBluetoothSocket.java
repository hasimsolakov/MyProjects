package com.example.hashim.ezpresenter.Models;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.example.hashim.ezpresenter.Interfaces.ISocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by Hashim on 11.7.2016 г..
 */
public class CustomBluetoothSocket implements ISocket {
    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothSocket bluetoothSocket;

    public CustomBluetoothSocket(BluetoothAdapter bluetoothAdapter, String deviceNameToConnect){
        BluetoothDevice device = this.getDeviceByName(bluetoothAdapter, deviceNameToConnect);
       this.bluetoothSocket = this.createSocket(device);
    }

    public boolean isConnected(){
       return this.bluetoothSocket.isConnected();
    }

    public void connect() throws IOException{
        this.bluetoothSocket.connect();
    }

    public void close() throws IOException{
        this.bluetoothSocket.close();
    }

    public InputStream getInputStream() throws IOException {
        return this.bluetoothSocket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException{
        return this.bluetoothSocket.getOutputStream();
    }

    private BluetoothDevice getDeviceByName(BluetoothAdapter bluetoothAdapter, String deviceName){
        BluetoothDevice deviceToConnect = null;
        for (BluetoothDevice device : bluetoothAdapter.getBondedDevices() ) {
            if (device.getName().equals(deviceName)){
                deviceToConnect = device;
                break;
            }
        }

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceToConnect.getAddress());
        bluetoothAdapter.cancelDiscovery();
        return device;
    }

    private BluetoothSocket createSocket(BluetoothDevice device){
        BluetoothSocket bluetoothSocket = null;
        try{
            bluetoothSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
        }catch (IOException ex){
            Log.e("Error", "Problem occurred while establishing connection");
        }

        return bluetoothSocket;
    }

}
