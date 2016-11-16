package presenter.Fatories;

import presenter.Interfaces.IInStreamFactory;
import presenter.framework.lifecycle.dependency.Component;

import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.InputStream;

@Component
public class InStreamFactory implements IInStreamFactory {
    @Override
    public InputStream create(StreamConnection connection) throws IOException {
        return connection.openInputStream();
    }
}
