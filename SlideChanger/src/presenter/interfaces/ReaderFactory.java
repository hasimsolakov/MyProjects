package presenter.interfaces;

import java.io.InputStream;

/**
 * Created by Hashim on 5.7.2016 г..
 */
public interface ReaderFactory {
    Reader create(InputStream instream);
}
