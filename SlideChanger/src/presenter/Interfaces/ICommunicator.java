package presenter.Interfaces;

import java.io.IOException;
import java.util.List;

public interface ICommunicator {
    String receive() throws IOException;
    void send(IOutput output);
    void send(List<IOutput> outputs);
}
