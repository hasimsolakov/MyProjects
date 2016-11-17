package com.example.hashim.ezpresenter.Presenters;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.example.hashim.ezpresenter.Factories.SocketFactory;
import com.example.hashim.ezpresenter.IO.SocketCommunicator;
import com.example.hashim.ezpresenter.Interfaces.IOutput;
import com.example.hashim.ezpresenter.Interfaces.ISocket;
import com.example.hashim.ezpresenter.Interfaces.ISocketCommunicator;
import com.example.hashim.ezpresenter.Interfaces.ISocketFactory;
import com.example.hashim.ezpresenter.Interfaces.IStreamReader;
import com.example.hashim.ezpresenter.Interfaces.ISelectPresentationView;
import com.example.hashim.ezpresenter.Interfaces.ISelectPresentationPresenter;
import com.example.hashim.ezpresenter.Interfaces.IStreamWriter;
import com.example.hashim.ezpresenter.IO.StreamWriter;
import com.example.hashim.ezpresenter.Models.Output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


public class SelectPresentationPresenter implements ISelectPresentationPresenter {
    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ISelectPresentationView view;
    private ISocketCommunicator socketCommunicator;

    public SelectPresentationPresenter(ISelectPresentationView view, BluetoothAdapter bluetoothAdapter, String selectedDeviceName){
        this.view = view;
        ISocketFactory factory = new SocketFactory();
        ISocket socket = factory.getBluetoothSocket(bluetoothAdapter, selectedDeviceName);
        socketCommunicator = SocketCommunicator.create(socket,this);
    }



    @Override
    public void showPresentationsNames(final ArrayList presentationsNamesList){

        view.runOnView(new Runnable() {
            @Override
            public void run() {
                view.showPresentationsListView(presentationsNamesList);
            }
        });
    }


    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {
        if (this.socketCommunicator != null){
            this.socketCommunicator.onDestroy();
            this.socketCommunicator = null;
        }

        if (this.view != null){
            this.view = null;
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void openPresentation(String name) {
        String command = "O";
        IOutput<String> output = new Output<String>(command,name);
        this.socketCommunicator.sendOutput(output);
        view.goToPresentationView();
    }





}
