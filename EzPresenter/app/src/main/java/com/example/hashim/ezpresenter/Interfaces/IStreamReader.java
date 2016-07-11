package com.example.hashim.ezpresenter.Interfaces;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hashim on 9.7.2016 г..
 */
public interface IStreamReader extends Closeable{
    String readLine();
}
