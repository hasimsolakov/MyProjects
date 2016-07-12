package com.example.hashim.ezpresenter.Interfaces;

import java.util.ArrayList;

/**
 * Created by Hashim on 9.7.2016 г..
 */
public interface ISelectPresentationPresenter {
    void showPresentationsNames(ArrayList presentationsNamesList);
    void onResume();
    void onDestroy();
    void onPause();
    void openPresentation(String name);
}
