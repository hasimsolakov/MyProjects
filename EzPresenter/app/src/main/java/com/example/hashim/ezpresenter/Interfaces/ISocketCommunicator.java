package com.example.hashim.ezpresenter.Interfaces;

import com.example.hashim.ezpresenter.Presenters.SelectPresentationPresenter;

/**
 * Created by Hashim on 11.7.2016 г..
 */
public interface ISocketCommunicator {
    <T> void sendOutput(IOutput<T> output);
    void onDestroy();

}
