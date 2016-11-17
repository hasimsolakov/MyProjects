package com.example.hashim.ezpresenter.Interfaces;


import android.view.View;

public interface IPresentationPresenter {
    void gotoNextSlide();
    void gotoPreviousSlide();
    void manipulateSlideShow(View view);
    void onDestroy();
}
