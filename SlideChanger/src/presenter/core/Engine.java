package presenter.core;

import presenter.customExceptions.ConnectionCouldNotBeEstablished;
import presenter.interfaces.*;
import presenter.framework.commandDispatcher.CommandDispatcher;
import presenter.framework.dependencyInjector.Container;
import presenter.framework.lifecycle.dependency.Component;
import presenter.framework.lifecycle.dependency.Inject;
import presenter.interfaces.Runnable;

import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class Engine implements Runnable {

    private static final String presentationsFolder = "/home/hashim/Desktop";

    private presenter.interfaces.Communicator communicator;

    @Inject
    private Container dependencyContainer;

    @Inject
    private CommandDispatcher commandDispatcher;

    @Inject
    private InStreamFactory inStreamFactory;

    @Inject
    private OutStreamFactory outStreamFactory;

    @Inject
    private ReaderFactory readerFactory;

    @Inject
    private WriterFactory writerFactory;

    public Engine() { }

    public void run() {
        try{
            StreamConnection connection = this.establishConnection();
            try(InputStream inputStream = this.inStreamFactory.create(connection)){
                try(OutputStream outputStream = this.outStreamFactory.create(connection)){
                    Writer writer = this.writerFactory.create(outputStream);
                    Reader reader = this.readerFactory.create(inputStream);
                    this.dependencyContainer.registerInstanceOfSuperClass(writer, Writer.class);
                    this.dependencyContainer.registerInstanceOfSuperClass(reader, Reader.class);
                    this.communicator = new DefaultCommunicator(writer, reader);
                    this.dependencyContainer.registerInstanceOfSuperClass(this.communicator, presenter.interfaces.Communicator.class);
                   List<Output> presentationsNames = this.getPresentations(new File(presentationsFolder));
                    Output end = new presenter.core.models.Output("end");
                    presentationsNames.add(end);
                    String outputType = "Presentations names";
                    Output output = new presenter.core.models.Output(outputType);
                    this.communicator.send(output);
                    this.communicator.send(presentationsNames);
                    String input;
                    while ((input = this.communicator.receive()) != null) {
                        this.commandDispatcher.dispatchCommand(input);
                    }
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            }

        }
        catch (ConnectionCouldNotBeEstablished ex){
            System.out.println("Problem occurred during establishing connection");
            return;
        }catch (IOException ex){
            System.out.println("Problem with streams! Terminating");
            return;
        }
    }

    private List<Output> getPresentations(File presentationsFolder){
        File [] files = presentationsFolder.listFiles();
        List<Output> presentationsNames = new ArrayList<>(files.length);
        for (File file:files) {
            presentationsNames.add(new presenter.core.models.Output(file.getName()));
        }

        return presentationsNames;
    }

    private StreamConnection establishConnection() throws ConnectionCouldNotBeEstablished{
        //Create a UUID for SPP
        UUID uuid = new UUID("1101", true);
        //Create the servicve url
        String connectionString = "btspp://localhost:" + uuid + ";name=Sample SPP Server";

        //open server url
        StreamConnectionNotifier streamConnNotifier;

        try{
            streamConnNotifier = (StreamConnectionNotifier) Connector.open(connectionString);
        }catch (IOException ioE){
            throw new ConnectionCouldNotBeEstablished(ioE);
        }
        //Wait for client connection
        System.out.println("\nServer Started. Waiting for clients to connect...");
        try{
            StreamConnection connection = streamConnNotifier.acceptAndOpen();
            RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
            System.out.println("Remote device address: " + dev.getBluetoothAddress());
            System.out.println("Remote device name: " + dev.getFriendlyName(true));
            return connection;
        } catch (IOException e) {
            throw new ConnectionCouldNotBeEstablished(e);
        }
    }
}
