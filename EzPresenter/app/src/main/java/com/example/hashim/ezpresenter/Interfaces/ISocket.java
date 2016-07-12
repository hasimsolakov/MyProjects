package com.example.hashim.ezpresenter.Interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Hashim on 11.7.2016 г..
 */
public interface ISocket {
    void connect() throws IOException;
    void close() throws IOException;
    InputStream getInputStream() throws IOException;
    OutputStream getOutputStream() throws IOException;
    boolean isConnected();
}
