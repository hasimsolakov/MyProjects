package com.example.hashim.ezpresenter.Presenters;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.example.hashim.ezpresenter.Interfaces.IMainPresenter;
import com.example.hashim.ezpresenter.Interfaces.IMainView;

import java.util.Set;

public class MainPresenter implements IMainPresenter {
    private BluetoothAdapter bluetoothAdapter;
    private IMainView mainView;

    public MainPresenter(IMainView mainView, BluetoothAdapter bluetoothAdapter){
        this.mainView = mainView;
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public void onResume(){
        this.mainView.showActiveDevices(this.getActiveDevices());
    }

    public String[]  getActiveDevices(){
        Set<BluetoothDevice> devicesSet = bluetoothAdapter.getBondedDevices();
        String[] devicesNames = new String [devicesSet.size()];
        int index = 0;
        for (BluetoothDevice device : devicesSet) {
            devicesNames[index] = device.getName();
            index++;
        }

        return devicesNames;
    }

    @Override
    public void onDestroy() {
        this.bluetoothAdapter = null;
        this.mainView = null;
    }
}
