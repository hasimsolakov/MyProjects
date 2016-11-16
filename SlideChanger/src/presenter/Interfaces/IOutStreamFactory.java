package presenter.Interfaces;

import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Hashim on 5.7.2016 г..
 */
public interface IOutStreamFactory {
    OutputStream create(StreamConnection connection) throws IOException;
}
