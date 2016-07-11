package com.example.hashim.ezpresenter.Interfaces;

import java.io.Closeable;
import java.io.OutputStream;

/**
 * Created by Hashim on 9.7.2016 г..
 */
public interface IStreamWriter extends Closeable {
    void writeLine(Object data);
}
