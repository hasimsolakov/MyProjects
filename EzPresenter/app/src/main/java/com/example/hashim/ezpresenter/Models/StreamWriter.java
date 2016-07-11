package com.example.hashim.ezpresenter.Models;

import com.example.hashim.ezpresenter.Interfaces.IStreamWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by Hashim on 9.7.2016 г..
 */
public class StreamWriter implements IStreamWriter {
    private static IStreamWriter instance;
    private PrintWriter writer;

    protected StreamWriter(OutputStream outputStream){
        this.writer = new PrintWriter(outputStream);
    }

    @Override
    public void close() throws IOException {
        this.writer.close();
    }

    public static IStreamWriter create(OutputStream outputStream){
        if (instance == null){
            instance = new StreamWriter(outputStream);
        }

        return instance;
    }

    @Override
    public void writeLine(Object data) {
        this.writer.println(data);
    }
}
