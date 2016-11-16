package presenter.framework.lifecycle.controller;


import presenter.framework.lifecycle.command.CommandMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Hashim on 20.8.2016 г..
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandMapping {
    CommandMethod value();
    String commandParameters() default "";
}
