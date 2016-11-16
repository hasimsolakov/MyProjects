package presenter.factories;


import presenter.IO.DefaultWriter;
import presenter.interfaces.Writer;
import presenter.interfaces.WriterFactory;
import presenter.framework.lifecycle.dependency.Component;

import java.io.OutputStream;

@Component
public class DefaultWriterFactory implements WriterFactory {
    @Override
    public Writer create(OutputStream outStream) {
        return new DefaultWriter(outStream);
    }
}
