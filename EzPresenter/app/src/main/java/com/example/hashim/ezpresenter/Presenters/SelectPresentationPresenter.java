package com.example.hashim.ezpresenter.Presenters;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hashim.ezpresenter.Interfaces.IStreamReader;
import com.example.hashim.ezpresenter.Interfaces.ISelectPresentationView;
import com.example.hashim.ezpresenter.Interfaces.ISelectPresentationPresenter;
import com.example.hashim.ezpresenter.Interfaces.IStreamWriter;
import com.example.hashim.ezpresenter.Models.StreamReader;
import com.example.hashim.ezpresenter.Models.StreamWriter;
import com.example.hashim.ezpresenter.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Hashim on 9.7.2016 г..
 */
public class SelectPresentationPresenter implements ISelectPresentationPresenter {
    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ISelectPresentationView view;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private String selectedDeviceName;
    private IStreamWriter writer;
    private IStreamReader reader;

    public SelectPresentationPresenter(ISelectPresentationView view, BluetoothAdapter bluetoothAdapter, String selectedDeviceName){
        this.view = view;
        this.bluetoothAdapter = bluetoothAdapter;
        this.selectedDeviceName = selectedDeviceName;
        ConnectionEstablishingThread connectionEstablishingThread = new ConnectionEstablishingThread(this.bluetoothAdapter);
        new Thread(connectionEstablishingThread).start();
    }


    private class ConnectionEstablishingThread implements Runnable{
        private BluetoothAdapter bluetoothAdapter;

        public ConnectionEstablishingThread(BluetoothAdapter bluetoothAdapter){
            this.bluetoothAdapter = bluetoothAdapter;
        }

        @Override
        public void run() {
            BluetoothDevice device = this.getDeviceByName(selectedDeviceName);
            bluetoothSocket = this.createSocket(device);
            try{
                bluetoothSocket.connect();
                InputListenerThread inputListenerThread = new InputListenerThread(bluetoothSocket);
                new Thread(inputListenerThread).start();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

        private BluetoothDevice getDeviceByName(String deviceName){
            BluetoothDevice deviceToConnect = null;
            for (BluetoothDevice device : this.bluetoothAdapter.getBondedDevices() ) {
                if (device.getName().equals(deviceName)){
                    deviceToConnect = device;
                    break;
                }
            }

            return this.bluetoothAdapter.getRemoteDevice(deviceToConnect.getAddress());
        }

        private BluetoothSocket createSocket(BluetoothDevice device){
            BluetoothSocket bluetoothSocket = null;
            try{
                bluetoothSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
                this.bluetoothAdapter.cancelDiscovery();
            }catch (IOException ex){
                Log.e("Error","Problem occurred while establishing connectiion");
                view.finish();
            }

            return bluetoothSocket;
        }
    }

    private class InputListenerThread implements Runnable{
        private BluetoothSocket bluetoothSocket;

        public InputListenerThread(BluetoothSocket bluetoothSocket){
            this.bluetoothSocket = bluetoothSocket;
        }

        @Override
        public void run() {
            try {
                reader = StreamReader.create(bluetoothSocket.getInputStream());
                String inputType = reader.readLine();
                while(inputType != null){
                    switch (inputType){
                        case "Presentations names": this.showPresentationsNames();
                    }

                    inputType = reader.readLine();
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
            finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void showPresentationsNames(){
            final ArrayList<String> presentationsNamesList = new ArrayList<>();
            String presentationName;
            while(!(presentationName = reader.readLine()).equals("end")){
                presentationsNamesList.add(presentationName);
            }

            String hello = "hello";
            view.runOnView(new Runnable() {
                @Override
                public void run() {
                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_item, presentationsNamesList);
                    ListView presentationsListView = view.getPresentationsListView();
                    presentationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            TextView name = (TextView) view;
                            String presentationToOpenName = name.getText().toString();
                            openPresentation(presentationToOpenName);
                        }
                    });
                }
            });
        }
    }

    private class ReplyThread implements Runnable{
        private BluetoothSocket bluetoothSocket;
        private String message;

        public ReplyThread(BluetoothSocket bluetoothSocket, String message){
            this.bluetoothSocket = bluetoothSocket;
            this.message = message;
        }

        @Override
        public void run() {
            try{
                writer = StreamWriter.create(this.bluetoothSocket.getOutputStream());
                writer.writeLine(this.message);
            }catch(IOException ex){
                ex.printStackTrace();
            }
            finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {
        if (this.bluetoothSocket != null){
            try {
                this.bluetoothSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void openPresentation(String name) {
    }





}
