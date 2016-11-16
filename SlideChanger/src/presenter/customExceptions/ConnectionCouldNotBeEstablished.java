package presenter.customExceptions;

/**
 * Created by Hashim on 5.7.2016 г..
 */
public class ConnectionCouldNotBeEstablished extends Exception {
    public ConnectionCouldNotBeEstablished(String message) {
        super(message);
    }

    public ConnectionCouldNotBeEstablished(Throwable cause) {
        super(cause);
    }

    public ConnectionCouldNotBeEstablished(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionCouldNotBeEstablished() {
    }
}
