package presenter.framework.dependencyInjector;


import presenter.framework.lifecycle.dependency.Component;
import presenter.framework.lifecycle.dependency.Inject;
import presenter.framework.parser.AnnotationParser;
import presenter.framework.parser.AnnotationParserImpl;
import presenter.framework.parser.strategies.ComponentParserStrategy;
import presenter.provider.type.TypeProvider;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Component
public class DependencyContainer implements Container {

    private AnnotationParser parser;

    private TypeProvider provider;

    private Map<Class, Class> components;

    private Map<Class, Object> cachedComponents;

    public DependencyContainer(TypeProvider provider, Class<? extends AnnotationParser> concreteAnnotationParserClazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        this.cachedComponents = new HashMap<>();
        this.components =  new HashMap<>();
        this.components.put(Container.class, DependencyContainer.class);
        this.cachedComponents.put(this.getClass(), this);
        this.provider = provider;
        this.parser = this.resolveDependencies(concreteAnnotationParserClazz);
        this.components.put(AnnotationParser.class, AnnotationParserImpl.class);
        this.cachedComponents.put(this.parser.getClass(), this.parser);
        this.fillComponents();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> superClassToInstance) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> primaryClassToInstance = components.get(superClassToInstance);
        if (primaryClassToInstance == null) {
            primaryClassToInstance = superClassToInstance;
        }
        T resolvedInstance = null;
        if (!this.cachedComponents.containsKey(primaryClassToInstance)){
             resolvedInstance = this.resolveDependencies((Class<? extends T>) primaryClassToInstance);
            this.cachedComponents.put(primaryClassToInstance, resolvedInstance);
        }
        else {
            resolvedInstance = (T) this.cachedComponents.get(primaryClassToInstance);
        }

        return resolvedInstance;
    }


    public <T> void registerInstanceOfSuperClass(T instance, Class<? super T> instanceSuperClass) {
        Class instanceClass = this.components.get(instanceSuperClass);
        if (instanceClass == null) {
            this.cachedComponents.put(instanceSuperClass, instance);
        }
        else {
            this.cachedComponents.put(instanceClass, instance);
        }
        
    }

    private void fillComponents() throws IllegalAccessException, InstantiationException {
        this.parser.parse(
                new ComponentParserStrategy(this.provider),
                this.components
        );
    }



    private <T> T resolveDependencies(Class<T> classToResolve) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        T instance = this.constructorInject(classToResolve);
        this.fieldInject(instance);
        return instance;
    }

    @SuppressWarnings("unchecked")
    private <T> void fieldInject(T instance) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                T instanceToInject = this.getInstance((Class<T>) field.getType());
                field.setAccessible(true);
                field.set(instance, instanceToInject);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T constructorInject(Class<T> classIntoInject) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor[] constructors = classIntoInject.getConstructors();
        for (Constructor construct: constructors){
            if (construct.isAnnotationPresent(Inject.class)){
                Parameter[] parameters = construct.getParameters();
                Object[] argumentsToPass = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    Object instance = this.getInstance(parameter.getType());
                    argumentsToPass[i] = instance;
                }

                return (T) construct.newInstance(argumentsToPass);
            }
        }

       return classIntoInject.newInstance();
    }

}
