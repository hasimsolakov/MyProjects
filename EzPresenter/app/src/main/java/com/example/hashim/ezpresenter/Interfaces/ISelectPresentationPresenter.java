package com.example.hashim.ezpresenter.Interfaces;

/**
 * Created by Hashim on 9.7.2016 г..
 */
public interface ISelectPresentationPresenter {
    void onResume();
    void onDestroy();
    void onPause();
    void openPresentation(String name);
}
