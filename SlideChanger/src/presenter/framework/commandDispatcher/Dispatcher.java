package presenter.framework.commandDispatcher;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Hashim on 21.8.2016 г..
 */
public interface Dispatcher {
    String dispatchCommand(String input)throws InvalidArgumentException, InvocationTargetException, IllegalAccessException, InstantiationException;
}
