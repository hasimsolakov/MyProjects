package presenter.interfaces;

import java.io.IOException;

/**
 * Created by Hashim on 5.7.2016 г..
 */
public interface Reader {
    String readLine() throws IOException;
    char read() throws IOException;
}
