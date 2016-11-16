package presenter.controllers;

import presenter.interfaces.Communicator;
import presenter.framework.lifecycle.command.CommandMethod;
import presenter.framework.lifecycle.controller.CommandController;
import presenter.framework.lifecycle.controller.CommandMapping;
import presenter.framework.lifecycle.dependency.Inject;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@CommandController
public class FileCommandsController {

    private final static String presentationFolder = "/home/hashim/Desktop";

    @Inject
    private Communicator communicator;

    @CommandMapping(CommandMethod.OPEN_PRESENTATION)
    public void openPresentationCommand() {
        try {
            String name = this.communicator.receive();
            Desktop myDesktop = Desktop.getDesktop();
            File presentation = new File(presentationFolder + "/" + name);
            myDesktop.open(presentation);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
