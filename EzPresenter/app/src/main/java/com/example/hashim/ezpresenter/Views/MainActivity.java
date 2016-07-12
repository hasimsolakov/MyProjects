package com.example.hashim.ezpresenter.Views;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hashim.ezpresenter.Interfaces.IMainPresenter;
import com.example.hashim.ezpresenter.Interfaces.IMainView;
import com.example.hashim.ezpresenter.Presenters.MainPresenter;
import com.example.hashim.ezpresenter.R;

public class MainActivity extends AppCompatActivity implements IMainView {

    private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private static int REQUEST_ENABLE_BT = 1;
    private IMainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

        }

        boolean bluetoothIsOn = this.bluetoothIsActive(bluetoothAdapter);
        if (bluetoothIsOn) {
            this.mainPresenter = new MainPresenter(this, bluetoothAdapter);
            this.mainPresenter.onResume();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.mainPresenter != null){
            this.mainPresenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                this.mainPresenter = new MainPresenter(this, bluetoothAdapter);
                this.mainPresenter.onResume();
            } else {
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        this.mainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showActiveDevices(String[] devicesNames) {


        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, devicesNames);
        ListView bluetoothDevicesListView = (ListView) findViewById(R.id.bluetoothDevicesList);

        bluetoothDevicesListView.setAdapter(stringArrayAdapter);
        bluetoothDevicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView deviceSelected = (TextView) view;
                String deviceName = deviceSelected.getText().toString();
                Intent intent = new Intent(getApplicationContext(), SelectPresentationView.class);
                intent.putExtra("DEVICE_NAME", deviceName);
                startActivity(intent);
            }
        });

    }


    private boolean bluetoothIsActive(BluetoothAdapter bluetoothAdapter) {
        if (this.bluetoothAdapter == null) {
            Log.e("Fatal Error", "Bluetooth Not supported. Aborting.");
            finish();
        } else {
            if (bluetoothAdapter.isEnabled()) {
                return true;
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(this.bluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, MainActivity.REQUEST_ENABLE_BT);
            }
        }

        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
