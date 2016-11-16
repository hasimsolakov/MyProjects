package presenter.Fatories;


import presenter.IO.DefaultWriter;
import presenter.Interfaces.IWriter;
import presenter.Interfaces.IWriterFactory;
import presenter.framework.lifecycle.dependency.Component;

import java.io.OutputStream;

@Component
public class WriterFactory implements IWriterFactory {
    @Override
    public IWriter create(OutputStream outStream) {
        return new DefaultWriter(outStream);
    }
}
