package com.example.hashim.ezpresenter.IO;

import com.example.hashim.ezpresenter.Interfaces.IStreamReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Hashim on 9.7.2016 г..
 */
public class StreamReader implements IStreamReader {
    private static IStreamReader instance;
    private BufferedReader reader;
    private String [] lines;
    private boolean isOpen;

    protected StreamReader(InputStream inputStream){
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.isOpen = true;
    }

    public static IStreamReader create(InputStream inputStream) {
        if (instance == null){
            instance = new StreamReader(inputStream);
        }

        return instance;
    }

    public boolean isOpen(){
        return this.isOpen;
    }

    @Override
    public String readLine() {
        try {
            return this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() throws IOException {
        this.isOpen = false;
        this.reader.close();
    }
}
