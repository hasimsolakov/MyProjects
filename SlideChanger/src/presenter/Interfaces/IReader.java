package presenter.Interfaces;

import java.io.IOException;

/**
 * Created by Hashim on 5.7.2016 г..
 */
public interface IReader {
    String readLine() throws IOException;
    char read() throws IOException;
}
