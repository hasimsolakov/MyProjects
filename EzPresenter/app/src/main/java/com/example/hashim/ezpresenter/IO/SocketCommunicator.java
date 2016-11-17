package com.example.hashim.ezpresenter.IO;

import com.example.hashim.ezpresenter.Interfaces.IOutput;
import com.example.hashim.ezpresenter.Interfaces.IPresentationPresenter;
import com.example.hashim.ezpresenter.Interfaces.ISelectPresentationPresenter;
import com.example.hashim.ezpresenter.Interfaces.ISocket;
import com.example.hashim.ezpresenter.Interfaces.ISocketCommunicator;
import com.example.hashim.ezpresenter.Interfaces.IStreamReader;
import com.example.hashim.ezpresenter.Interfaces.IStreamWriter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hashim on 11.7.2016 г..
 */
public class SocketCommunicator implements ISocketCommunicator {
    private static SocketCommunicator instance;
    private ISocket socket;
    private IStreamWriter writer;
    private IStreamReader reader;
    private ISelectPresentationPresenter selectPresentationPresenter;
    private IPresentationPresenter presentationPresenter;
    private Thread replyThread;
    private Thread listenerThread;

    private SocketCommunicator(ISocket socket, ISelectPresentationPresenter selectPresentationPresenter){
        this.socket = socket;
        this.selectPresentationPresenter = selectPresentationPresenter;
        InputListenerThread listener = new InputListenerThread(this.socket);
        this.listenerThread = new Thread(listener);
        this.listenerThread.start();
    }

    public static ISocketCommunicator create(ISocket socket, ISelectPresentationPresenter selectPresentationPresenter){
        if (instance == null){
            instance = new SocketCommunicator(socket, selectPresentationPresenter);
        }

        return instance;
    }

    public static ISocketCommunicator create(IPresentationPresenter presentationPresenter){
        if (instance == null) {
            throw new ExceptionInInitializerError("Instance should be instanced firstly with the SelectPresentationPresenter");
        }

        instance.setPresentationPresenter(presentationPresenter);
        return instance;
    }

    @Override
    public <T> void sendOutput(IOutput<T> output) {
        ReplyThread replier = new ReplyThread(this.socket, output);
        this.replyThread = new Thread(replier);
        this.replyThread.start();
    }


    public void onDestroy() {
        if(this.replyThread.isAlive()){
            try {
                this.replyThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (this.listenerThread.isAlive()){
            this.listenerThread.interrupt();
            try {
                this.listenerThread.join();
                if (this.writer.isOpen()){
                    this.writer.close();
                }

                if (this.reader.isOpen()){
                    this.reader.close();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (this.socket != null){
            if (this.socket.isConnected()){
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setPresentationPresenter(IPresentationPresenter presentationPresenter){
        this.presentationPresenter = presentationPresenter;
    }

    //region Threads

    private class InputListenerThread implements Runnable{
        private ISocket socket;

        public InputListenerThread(ISocket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                this.socket.connect();
                reader = StreamReader.create(this.socket.getInputStream());
                String inputType = reader.readLine();
                while(inputType != null || !listenerThread.isInterrupted()){
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

        public void showPresentationsNames(){
            final ArrayList<String> presentationsNamesList = new ArrayList<>();
            String presentationName;
            while(!(presentationName = reader.readLine()).equals("end")){
                presentationsNamesList.add(presentationName);
            }

            if (selectPresentationPresenter == null){
                throw new NullPointerException("Needed Presenter in not instanced");
            }

            selectPresentationPresenter.showPresentationsNames(presentationsNamesList);
        }
    }

    private class ReplyThread implements Runnable{
        private ISocket socket;
        private IOutput output;

        public ReplyThread(ISocket socket, IOutput output){
            this.socket = socket;
            this.output = output;
        }

        @Override
        public void run() {
            try{
                writer = StreamWriter.create(this.socket.getOutputStream());
                writer.writeLine(this.output);
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    //endregion

}
