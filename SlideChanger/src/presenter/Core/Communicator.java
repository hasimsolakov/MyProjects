package presenter.Core;


import presenter.Interfaces.*;
import presenter.framework.lifecycle.dependency.Component;
import presenter.framework.lifecycle.dependency.Inject;

import java.io.IOException;
import java.util.List;

@Component
public class Communicator implements ICommunicator {

    @Inject
    private IWriter writer;

    @Inject
    private IReader reader;

    public Communicator(IWriter writer, IReader reader) {
        this.writer = writer;
        this.reader = reader;
    }


    @Override
    public String receive() throws IOException {
        String input = this.reader.readLine();
        return input;
    }

    @Override
    public void send(IOutput output) {
        this.writer.writeLine(output.getData());
    }

    @Override
    public void send(List<IOutput> outputs){
        for (IOutput output:outputs) {
            this.send(output);
        }
    }
}
