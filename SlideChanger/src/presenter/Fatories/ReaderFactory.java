package presenter.Fatories;


import presenter.IO.DefaultReader;
import presenter.Interfaces.IReader;
import presenter.Interfaces.IReaderFactory;
import presenter.framework.lifecycle.dependency.Component;

import java.io.InputStream;

@Component
public class ReaderFactory implements IReaderFactory {
    @Override
    public IReader create(InputStream inStream) {
        return new DefaultReader(inStream);
    }
}
