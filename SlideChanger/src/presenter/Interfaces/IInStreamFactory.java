package presenter.Interfaces;

import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hashim on 5.7.2016 г..
 */
public interface IInStreamFactory {
    InputStream create(StreamConnection connection) throws IOException;
}
