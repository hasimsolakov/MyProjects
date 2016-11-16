package presenter.factories;

import presenter.framework.lifecycle.dependency.Component;

import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.InputStream;

@Component
public class DefaultInStreamFactory implements presenter.interfaces.InStreamFactory {
    @Override
    public InputStream create(StreamConnection connection) throws IOException {
        return connection.openInputStream();
    }
}
