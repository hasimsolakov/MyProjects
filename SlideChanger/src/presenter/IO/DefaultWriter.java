package presenter.IO;

import presenter.Interfaces.IWriter;
import presenter.framework.lifecycle.dependency.Component;

import java.io.OutputStream;
import java.io.PrintWriter;

@Component
public class DefaultWriter implements IWriter {
    private PrintWriter writer;

    public DefaultWriter(OutputStream outStream) {
        writer = new PrintWriter(outStream);
    }

    public PrintWriter getWriter(){
        return writer;
    }

    @Override
    public void writeLine(Object line) {
        this.getWriter().println(line);
        this.getWriter().flush();
    }

}
