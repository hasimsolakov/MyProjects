package com.example.hashim.ezpresenter.Presenters;

import android.view.View;
import android.widget.Button;

import com.example.hashim.ezpresenter.IO.SocketCommunicator;
import com.example.hashim.ezpresenter.Interfaces.IOutput;
import com.example.hashim.ezpresenter.Interfaces.IPresentationPresenter;
import com.example.hashim.ezpresenter.Interfaces.IPresentationView;
import com.example.hashim.ezpresenter.Interfaces.ISocketCommunicator;
import com.example.hashim.ezpresenter.Models.Output;


public class PresentationPresenter implements IPresentationPresenter {
    private final static String START_SLIDE_SHOW_MSG = "Start SlideShow";
    private final static String EXIT_SLIDE_SHOW_MSG = "Exit SlideShow";

    private IPresentationView presentationView;
    private ISocketCommunicator socketCommunicator;

    public PresentationPresenter(IPresentationView view) {
        this.presentationView = view;
        this.socketCommunicator = SocketCommunicator.create(this);
    }

    @Override
    public void gotoNextSlide() {
        String command = "N";
        IOutput output = new Output(command, null);
        this.socketCommunicator.sendOutput(output);
    }

    @Override
    public void gotoPreviousSlide() {
        String command = "P";
        IOutput output = new Output(command, null);
        this.socketCommunicator.sendOutput(output);
    }

    @Override
    public void manipulateSlideShow(View view) {
        Button slideShowButton = (Button) view;
        String currentText = slideShowButton.getText().toString();
        if (currentText.equals(START_SLIDE_SHOW_MSG)){
            this.startSlideShow();
            slideShowButton.setText(EXIT_SLIDE_SHOW_MSG);
        }
        else {
            this.exitSlideShow();
            slideShowButton.setText(START_SLIDE_SHOW_MSG);
        }

    }

    @Override
    public void onDestroy() {
        if (this.socketCommunicator != null){
            this.socketCommunicator.onDestroy();
            this.socketCommunicator = null;
        }

        if (this.presentationView != null){
            this.presentationView = null;
        }
    }

    private void startSlideShow() {
        String command = "1";
        IOutput output = new Output(command, null);
        this.socketCommunicator.sendOutput(output);
    }

    private void exitSlideShow() {
        String command = "2";
        IOutput output = new Output(command, null);
        this.socketCommunicator.sendOutput(output);
    }
}
