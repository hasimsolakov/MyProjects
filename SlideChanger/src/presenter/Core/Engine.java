package presenter.Core;

import presenter.Core.Models.Output;
import presenter.CustomExceptions.ConnectionCouldNotBeEstablished;
import presenter.Interfaces.*;
import presenter.framework.commandDispatcher.CommandDispatcher;
import presenter.framework.dependencyInjector.Container;
import presenter.framework.lifecycle.dependency.Component;
import presenter.framework.lifecycle.dependency.Inject;

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
public class Engine implements IEngine {

    private ICommunicator communicator;

    @Inject
    private Container dependencyContainer;

    @Inject
    private CommandDispatcher commandDispatcher;

    @Inject
    private IInStreamFactory inStreamFactory;

    @Inject
    private IOutStreamFactory outStreamFactory;

    @Inject
    private IReaderFactory readerFactory;

    @Inject
    private IWriterFactory writerFactory;

    public Engine() { }

    public void run() {
        try{
            StreamConnection connection = this.establishConnection();
            try(InputStream inputStream = this.inStreamFactory.create(connection)){
                try(OutputStream outputStream = this.outStreamFactory.create(connection)){
                    IWriter writer = this.writerFactory.create(outputStream);
                    IReader reader = this.readerFactory.create(inputStream);
                    this.dependencyContainer.registerInstanceOfSuperClass(writer, IWriter.class);
                    this.dependencyContainer.registerInstanceOfSuperClass(reader, IReader.class);
                    this.communicator = new Communicator(writer, reader);
                    this.dependencyContainer.registerInstanceOfSuperClass(this.communicator, ICommunicator.class);
                   List<IOutput> presentationsNames = this.getPresentations(new File("/home/hashim/Desktop")); //TODO
                    IOutput end = new Output<>("end");
                    presentationsNames.add(end);
                    String outputType = "Presentations names";
                    IOutput output = new Output<>(outputType);
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

    private List<IOutput> getPresentations(File presentationsFolder){
        File [] files = presentationsFolder.listFiles();
        List<IOutput> presentationsNames = new ArrayList<>(files.length);
        for (File file:files) {
            presentationsNames.add(new Output<>(file.getName()));
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
