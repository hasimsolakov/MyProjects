package presenter;

import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.imageio.ImageIO;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Class that implements an SPP Server which accepts single line of
 * message from an SPP client and sends a single line of response to the client.
 */
public class SimpleSPPServer {
    StreamConnection connection;
    OutputStream outStream = null;
    InputStream inStream = null;
    int currentSlide = 0;
    String presentationFolder = "C:\\Users\\Hashim\\Desktop\\Presentations";
    String [] notesCollection;

    //start server
    public void startServer() throws IOException {

        //Create a UUID for SPP
        UUID uuid = new UUID("1101", true);
        //Create the servicve url
        String connectionString = "btspp://localhost:" + uuid + ";name=Sample SPP Server";

        //open server url
        StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier) Connector.open(connectionString);

        //Wait for client connection
        System.out.println("\nServer Started. Waiting for clients to connect...");
        connection = streamConnNotifier.acceptAndOpen();

        RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
        System.out.println("Remote device address: " + dev.getBluetoothAddress());
        System.out.println("Remote device name: " + dev.getFriendlyName(true));
//......//
        //read string from spp client
        outStream = connection.openOutputStream();
        inStream = connection.openInputStream();

        PrintWriter writer = new PrintWriter(outStream);
        BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
        String lineRead = bReader.readLine();
        File presentation = new File(presentationFolder);
        File [] files = presentation.listFiles();
        List<String> presentationsNames = new ArrayList<>(files.length);
        for (File file:files) {
            presentationsNames.add(file.getName());
        }

        for (String name :
                presentationsNames) {
            this.sendMessage(writer,name);
        }

        this.sendMessage(writer,"end");
        System.out.println(lineRead);
        try {
            Robot controller = new Robot();
            while (true) {
                boolean isReady = bReader.ready();
                while (!isReady) {
                    isReady = bReader.ready();
                }

                char command = (char) bReader.read();
                if (command == 'N') {
                    controller.keyPress(KeyEvent.VK_RIGHT);
                    currentSlide++;
                   // this.SendImage(presentationFolder + "\\slide-" + currentSlide + ".png");
                    System.out.println(presentationFolder);
                } else if (command == 'P') {
                    controller.keyPress(KeyEvent.VK_LEFT);
                    currentSlide--;
                   // this.SendImage(presentationFolder + "\\slide-" + currentSlide + ".png");
                } else if (command == '1') {
                    controller.keyPress(KeyEvent.VK_F5);
                    currentSlide = 1;
                    //this.SendImage(presentationFolder + "\\slide-" + currentSlide + ".png");
                    System.out.println(presentationFolder);
                } else if (command == '2') {
                    controller.keyPress(KeyEvent.VK_ESCAPE);
                    currentSlide = 0;
                } else if (command == 'O') {
                    char[] fileName = new char[20];
                    bReader.read(fileName);
                    String name = new String(fileName);
                    name = name.replace("\u0000", "");
                    Desktop myDesktop = Desktop.getDesktop();
                    presentation = new File(presentationFolder + "\\" + name);
                    myDesktop.open(presentation);
                   // SlideExtractor extractor = new SlideExtractor(presentationFolder + "\\" + name);
                 //  String notes = extractor.extractSlidesNotes();
                   // notesCollection = notes.split("\\*");
                    //for (String note :notesCollection) {
                    //    if (!note.equals("")){
                     //       note = note.replaceAll("\\n", "");
                    //        this.sendMessage(writer,note);
                      //  }
                   // }

                  // this.sendMessage(writer,"end");
                }
            }
        } catch (Exception ex) {
            System.out.println("Problem with the robot");
            System.exit(0);
        } finally {
            writer.close();
            bReader.close();
            inStream.close();
            outStream.close();
            streamConnNotifier.close();
            connection.close();
        }
    }

    private void sendMessage(PrintWriter writer, String message){
        writer.println(message);
        writer.flush();
    }

    private void SendImage(String imagePath) {
        //send response to spp client
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            ImageIO.write(image, "PNG", outStream);
        } catch (IOException ioEx) {
            System.out.println("Error with the outStream");
        }
    }
}
