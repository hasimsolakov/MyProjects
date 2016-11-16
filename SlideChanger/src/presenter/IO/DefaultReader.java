package presenter.IO;


import presenter.interfaces.Reader;
import presenter.framework.lifecycle.dependency.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class DefaultReader implements Reader {

    private BufferedReader reader;

    public DefaultReader(InputStream inStream) {
        this.reader = new BufferedReader(new InputStreamReader(inStream));
    }

    public BufferedReader getReader() {
        return this.reader;
    }

    @Override
    public String readLine() throws IOException{
        return this.getReader().readLine();
    }

    @Override
    public char read() throws IOException {
        return (char) this.getReader().read();
    }
}
