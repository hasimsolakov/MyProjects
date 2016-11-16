package presenter.framework.parser.strategies;


import presenter.framework.lifecycle.command.CommandMethod;
import presenter.framework.lifecycle.controller.CommandController;
import presenter.framework.lifecycle.controller.CommandMapping;
import presenter.framework.lifecycle.controller.CommandParameter;
import presenter.framework.lifecycle.controller.ControllerMethodPair;
import presenter.framework.lifecycle.dependency.Component;
import presenter.framework.lifecycle.dependency.Inject;
import presenter.provider.type.TypeProvider;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ControllerParserStrategy implements AnnotationParserStrategy<CommandMethod, Map<String, ControllerMethodPair>> {

    private static final String PARAMETERS_SPLITTER = "\\|";

    private TypeProvider provider;

    @Inject
    public ControllerParserStrategy(TypeProvider provider){
        this.provider = provider;
    }

    @Override
    public void parse(Map<CommandMethod, Map<String, ControllerMethodPair>> result) throws IllegalAccessException, InstantiationException {
        for (Class currentClass : this.provider.getClassesByAnnotation(CommandController.class)) {
            for (Method currentMethod : Arrays.stream(currentClass.getDeclaredMethods()).filter(m -> m.isAnnotationPresent(CommandMapping.class)).toArray(Method[]::new)) {

                CommandMapping commandMapping
                        = currentMethod.getAnnotation(CommandMapping.class);

                CommandMethod commandMethod = commandMapping.value();
                String mapping = commandMapping.commandParameters();
                Parameter[] methodParams = currentMethod.getParameters();
                Map<String, Parameter> paramsToMap = new HashMap<>();
                for (Parameter param : methodParams) {
                    if (param.isAnnotationPresent(CommandParameter.class)) {
                        String paramToMapName = param.getAnnotation(CommandParameter.class).value();
                        paramsToMap.put(paramToMapName, param);
                    }
                }

                mapping = this.createRegex(mapping, paramsToMap);

                ControllerMethodPair pair = new ControllerMethodPair(currentClass, currentMethod);

                if (!result.containsKey(commandMethod)) {
                    result.put(commandMethod, new HashMap<>());
                }

                result.get(commandMethod).put(mapping, pair);
            }
        }
    }

    private String createRegex(String parameters, Map<String, Parameter> paramsToMap) {
        if (parameters.equals("")) {
            return parameters;
        }

        String[] tokens = parameters.split(PARAMETERS_SPLITTER);
        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("^");
        for (int index = 0; index < tokens.length; index++) {
            String parameterName = tokens[index];
            if (parameterName.equals("")) {
                continue;
            }

            if (parameterName.startsWith("{") && parameterName.endsWith("}")) {
                String paramNameToMap = parameterName.substring(1, parameterName.length() - 1);
                Parameter parameterToMap = paramsToMap.get(paramNameToMap);
                Class<?> type = parameterToMap.getType();

                if (type == int.class ||
                        type == Integer.class ||
                        type == long.class ||
                        type == Long.class) {
                    regexBuilder.append(PARAMETERS_SPLITTER);
                    regexBuilder.append("(?<");
                    regexBuilder.append(paramNameToMap);
                    regexBuilder.append(">\\d+)");
                } else if (type == double.class ||
                        type == Double.class ||
                        type == float.class ||
                        type == Float.class) {
                    regexBuilder.append(PARAMETERS_SPLITTER);
                    regexBuilder.append("(?<");
                    regexBuilder.append(paramNameToMap);
                    regexBuilder.append(">\\d+[.]?\\d+)");
                } else if (type == String.class) {
                    regexBuilder.append(PARAMETERS_SPLITTER);
                    regexBuilder.append("(?<");
                    regexBuilder.append(paramNameToMap);
                    regexBuilder.append(">\\w+)");
                } else {

                    throw new UnsupportedClassVersionError("Cant map this type of parameter");
                }
            } else {
                regexBuilder.append(PARAMETERS_SPLITTER);
                regexBuilder.append(parameterName);
            }
        }


        return regexBuilder.toString();
    }

}
