package presenter.interfaces;

import java.io.OutputStream;

/**
 * Created by Hashim on 5.7.2016 г..
 */
public interface WriterFactory {
    Writer create(OutputStream outStream);
}
