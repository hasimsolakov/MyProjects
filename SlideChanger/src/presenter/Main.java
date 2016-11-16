package presenter;

import presenter.framework.dependencyInjector.*;
import presenter.framework.parser.AnnotationParserImpl;
import presenter.interfaces.Runnable;
import presenter.provider.type.TypeProvider;
import presenter.provider.type.TypeProviderImpl;

import javax.bluetooth.LocalDevice;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public  class Main{

    public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //display local device address and name
        LocalDevice localDevice = LocalDevice.getLocalDevice();
        System.out.println("Address: "+localDevice.getBluetoothAddress());
        System.out.println("Name: "+localDevice.getFriendlyName());

        TypeProvider typeProvider = new TypeProviderImpl();
        Container dependencyContainer = new DependencyContainer(typeProvider, AnnotationParserImpl.class);
        Runnable engine = dependencyContainer.getInstance(Runnable.class);
        engine.run();
    }
}