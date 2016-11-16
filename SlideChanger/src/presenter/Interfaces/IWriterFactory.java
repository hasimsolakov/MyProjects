package presenter.Interfaces;

import java.io.OutputStream;

/**
 * Created by Hashim on 5.7.2016 г..
 */
public interface IWriterFactory {
    IWriter create(OutputStream outStream);
}
