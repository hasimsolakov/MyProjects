package presenter.Fatories;

import presenter.Interfaces.IOutStreamFactory;
import presenter.framework.lifecycle.dependency.Component;

import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class OutStreamFactory implements IOutStreamFactory {
    @Override
    public OutputStream create(StreamConnection connection) throws IOException {
        return connection.openOutputStream();
    }
}
