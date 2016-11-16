package presenter.controllers;

import presenter.framework.lifecycle.command.CommandMethod;
import presenter.framework.lifecycle.controller.CommandController;
import presenter.framework.lifecycle.controller.CommandMapping;
import presenter.framework.lifecycle.dependency.Inject;

import java.awt.*;
import java.awt.event.KeyEvent;

@CommandController
public class SlideCommandsController {

    @Inject
    private Robot robot;

    @CommandMapping(CommandMethod.NEXT_PAGE)
    public void gotoNextSlide() {
        this.robot.keyPress(KeyEvent.VK_RIGHT);
        this.robot.keyRelease(KeyEvent.VK_RIGHT);
    }

    @CommandMapping(CommandMethod.PREVIOUS_PAGE)
    public void gotoPreviousSlide() {
        this.robot.keyPress(KeyEvent.VK_LEFT);
        this.robot.keyRelease(KeyEvent.VK_LEFT);
    }

    @CommandMapping(CommandMethod.START_SLIDE_SHOW)
    public void startSlideShow() {
        this.robot.keyPress(KeyEvent.VK_F5);
        this.robot.keyRelease(KeyEvent.VK_F5);
    }

    @CommandMapping(CommandMethod.EXIT_SLIDE_SHOW)
    public void exitSlideShow() {
        this.robot.keyPress(KeyEvent.VK_ESCAPE);
        this.robot.keyRelease(KeyEvent.VK_ESCAPE);
    }

}
