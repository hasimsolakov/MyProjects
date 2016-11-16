package presenter.framework.lifecycle.controller;

import java.lang.reflect.Method;

public final class ControllerMethodPair {
    private final Method action;

    /**
     * The controller object with all of its
     * dependencies bundled
     */
    private final Class controller;
    /**
     * Association by position in the input string
     *    e.g.: /users/{4}/edit/{gosho}
     *    Arguments are on indices 2 and 4
     */

    public ControllerMethodPair(final Class controller, final Method action) {
        this.controller = controller;
        this.action = action;
    }

    public Method getMethod() {
        return action;
    }

    public Class getController() {
        return controller;
    }

}