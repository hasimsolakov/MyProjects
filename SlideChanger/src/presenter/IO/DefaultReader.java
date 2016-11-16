package presenter.IO;


import presenter.Interfaces.IReader;
import presenter.framework.lifecycle.dependency.Component;
import presenter.framework.lifecycle.dependency.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class DefaultReader implements IReader {

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
