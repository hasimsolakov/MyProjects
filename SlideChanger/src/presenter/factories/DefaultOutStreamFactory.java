package presenter.factories;

import presenter.framework.lifecycle.dependency.Component;

import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class DefaultOutStreamFactory implements presenter.interfaces.OutStreamFactory {
    @Override
    public OutputStream create(StreamConnection connection) throws IOException {
        return connection.openOutputStream();
    }
}
