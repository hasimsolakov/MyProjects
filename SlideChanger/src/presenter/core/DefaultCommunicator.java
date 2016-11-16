package presenter.core;


import presenter.interfaces.*;
import presenter.framework.lifecycle.dependency.Component;
import presenter.framework.lifecycle.dependency.Inject;

import java.io.IOException;
import java.util.List;

@Component
public class DefaultCommunicator implements presenter.interfaces.Communicator {

    @Inject
    private Writer writer;

    @Inject
    private Reader reader;

    public DefaultCommunicator(Writer writer, Reader reader) {
        this.writer = writer;
        this.reader = reader;
    }


    @Override
    public String receive() throws IOException {
        String input = this.reader.readLine();
        return input;
    }

    @Override
    public void send(Output output) {
        this.writer.writeLine(output.getData());
    }

    @Override
    public void send(List<Output> outputs){
        for (Output output:outputs) {
            this.send(output);
        }
    }
}
