package presenter.framework.commandDispatcher;


import presenter.Core.Models.Utils;
import presenter.framework.dependencyInjector.Container;
import presenter.framework.lifecycle.command.CommandMethod;
import presenter.framework.lifecycle.controller.CommandParameter;
import presenter.framework.lifecycle.controller.ControllerMethodPair;
import presenter.framework.lifecycle.dependency.Component;
import presenter.framework.lifecycle.dependency.Inject;
import presenter.framework.parser.AnnotationParser;
import presenter.framework.parser.strategies.ControllerParserStrategy;
import presenter.provider.type.TypeProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommandDispatcher implements Dispatcher{
    private static final String COMMAND_PARAMETERS_SPLITTER = "";

    private AnnotationParser parser;

    private TypeProvider provider;

    private Container container;

    private Map<CommandMethod, Map<String, ControllerMethodPair>> controllers;

    private Map<Class, Function<String, Object>> typeConversions;

    private Map<Class, Object> locatedControllers;

    @Inject
    public CommandDispatcher(AnnotationParser parser, Container container, TypeProvider typeProvider) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        this.container = container;
        this.provider = typeProvider;
        this.parser = parser;
        this.controllers = new HashMap<>();
        this.locatedControllers = new HashMap<>();
        this.fillControllers();
    }

    public String dispatchCommand(String input) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        String [] commandParamsPair = input.split(COMMAND_PARAMETERS_SPLITTER);
        CommandMethod commandMethod = null;
        try{
            commandMethod = CommandMethod.determineType(commandParamsPair[0]);
        } catch (IllegalArgumentException ex){
                throw new IllegalArgumentException("Couldn't match Command Name!", ex);
        }

        String arguments = null;
        if (commandParamsPair.length > 1){
            arguments = "|" + commandParamsPair[1];
        }

        Map<String, ControllerMethodPair> methods = this.controllers.get(commandMethod);

        String resultOfInvoke = null;
        if (arguments != null){
            for (Map.Entry<String, ControllerMethodPair> regexControllerMethodPair : methods.entrySet()) {
                String regex = regexControllerMethodPair.getKey();
                if (arguments.matches(regex)){
                    resultOfInvoke = (String) this.executeMethodWithParams(regexControllerMethodPair.getValue().getController(), regexControllerMethodPair.getValue().getMethod(), regex, arguments);
                    break;
                }
            }
        }
        else{
            for (Map.Entry<String, ControllerMethodPair> regexMethod : methods.entrySet()) {
                String regex = regexMethod.getKey();
                if (regex.equals("")) {                                         // REGEX IS EMPTY WHEN THERE ARE NO PARAMETERS TO MAP ARE IN THE METHOD
                    resultOfInvoke = (String) this.executeMethodWithoutParams(regexMethod.getValue().getController(), regexMethod.getValue().getMethod());
                }
            }
        }

        return resultOfInvoke;
    }

    private Object executeMethodWithoutParams(Class controller, Method method) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Object controllerInstance = this.container.getInstance(controller);
        return method.invoke(controllerInstance);
    }

    private Object executeMethodWithParams(Class controller, Method method, String regex, String arguments) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Parameter [] parameters = method.getParameters();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(arguments);
        if (matcher.matches()){
            Object[] argumentsToPass = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                if (parameter.isAnnotationPresent(CommandParameter.class)) {
                    String paramNameToMap = parameter.getAnnotation(CommandParameter.class).value();
                    String matchFromArguments = matcher.group(paramNameToMap);
                    argumentsToPass[i] = Utils.getConversions()
                            .get(parameter.getType())
                            .apply(matchFromArguments);
                }
            }

            Object controllerInstance = this.container.getInstance(controller);
            return method.invoke(controllerInstance, argumentsToPass);
        }
        else{
            return null;    // TODO: Throw appropriate exception
        }
    }

    private void fillControllers() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        this.parser.parse(
               new ControllerParserStrategy(this.provider),
                this.controllers
        );
    }
}
