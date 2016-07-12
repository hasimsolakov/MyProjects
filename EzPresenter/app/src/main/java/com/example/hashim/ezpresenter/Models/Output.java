package com.example.hashim.ezpresenter.Models;

import com.example.hashim.ezpresenter.Interfaces.IOutput;

/**
 * Created by Hashim on 11.7.2016 г..
 */
public class Output<T> implements IOutput<T>{
    private String command;
    private T data;

    public Output(String command, T data){
        this.setCommand(command);
        this.data = data;
    }

    public String getCommand(){
        return this.command;
    }

    public T getData(){
        return this.data;
    }

    @Override
    public String toString() {
        return this.command + this.data.toString();
    }

    private void setCommand(String command){
        this.command = command;
    }
}
