package presenter.interfaces;

import java.io.IOException;
import java.util.List;

public interface Communicator {
    String receive() throws IOException;
    void send(Output output);
    void send(List<Output> outputs);
}
