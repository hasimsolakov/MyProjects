package com.example.hashim.ezpresenter.Interfaces;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Hashim on 9.7.2016 г..
 */
public interface ISelectPresentationView {
    void goToPresentationView();
    void runOnView(Runnable action);
    void showPresentationsListView(ArrayList presentationsNamesList);
    View findViewById(int id);
    void finish();
}
