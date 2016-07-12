package com.example.hashim.ezpresenter.Views;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hashim.ezpresenter.Interfaces.ISelectPresentationView;
import com.example.hashim.ezpresenter.Interfaces.ISelectPresentationPresenter;
import com.example.hashim.ezpresenter.Presenters.SelectPresentationPresenter;
import com.example.hashim.ezpresenter.R;

import java.util.ArrayList;

/**
 * Created by Hashim on 9.7.2016 г..
 */
public class SelectPresentationView extends AppCompatActivity implements ISelectPresentationView {
    private ISelectPresentationPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_presentation);

        Intent intent = getIntent();
        String deviceName = intent.getStringExtra("DEVICE_NAME");
        this.presenter = new SelectPresentationPresenter(this, BluetoothAdapter.getDefaultAdapter(), deviceName);
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

    @Override
    protected void onResume() {
        super.onResume();
        if (this.presenter != null){
            this.presenter.onResume();
        }
    }

    public void runOnView(Runnable action){
        this.runOnUiThread(action);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy();

    }


    public void showPresentationsListView(ArrayList presentationsNamesList) {
            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, presentationsNamesList);
            ListView presentationsListView = (ListView) findViewById(R.id.presentations_list_view);
            presentationsListView.setAdapter(stringArrayAdapter);
            presentationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView name = (TextView) view;
                    String presentationToOpenName = name.getText().toString();
                    presenter.openPresentation(presentationToOpenName);
                }
            });
    }

    @Override
    public void goToPresentationView() {
        Intent intent = new Intent(this,PresentationView.class);
        startActivity(intent);
    }
}



