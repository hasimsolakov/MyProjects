package presenter.factories;


import presenter.IO.DefaultReader;
import presenter.interfaces.Reader;
import presenter.interfaces.ReaderFactory;
import presenter.framework.lifecycle.dependency.Component;

import java.io.InputStream;

@Component
public class DefaultReaderFactory implements ReaderFactory {
    @Override
    public Reader create(InputStream inStream) {
        return new DefaultReader(inStream);
    }
}
