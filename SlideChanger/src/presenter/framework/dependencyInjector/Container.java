package presenter.framework.dependencyInjector;


import java.lang.reflect.InvocationTargetException;

public interface Container {
    <T> T getInstance(Class<T> superClassToInstance) throws IllegalAccessException, InvocationTargetException, InstantiationException;
    <T> void registerInstanceOfSuperClass(T instance, Class<? super T> instanceClass);
}
