package presenter.Interfaces;

import java.io.InputStream;

/**
 * Created by Hashim on 5.7.2016 г..
 */
public interface IReaderFactory {
    IReader create(InputStream instream);
}
