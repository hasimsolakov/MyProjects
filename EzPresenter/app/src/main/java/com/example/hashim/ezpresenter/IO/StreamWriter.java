package com.example.hashim.ezpresenter.IO;

import com.example.hashim.ezpresenter.Interfaces.IOutput;
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
    private boolean isOpen;

    protected StreamWriter(OutputStream outputStream){
        this.writer = new PrintWriter(outputStream);
        this.isOpen = true;
    }

    public boolean isOpen(){
        return this.isOpen;
    }

    @Override
    public void close() throws IOException {
        this.isOpen = false;
        this.writer.close();
    }

    public static IStreamWriter create(OutputStream outputStream){
        if (instance == null){
            instance = new StreamWriter(outputStream);
        }

        return instance;
    }

    @Override
    public void writeLine(IOutput output) {
        this.writer.println(output.getCommand());
        this.writer.flush();
        this.writer.println(output.getData());
        this.writer.flush();
    }
}
