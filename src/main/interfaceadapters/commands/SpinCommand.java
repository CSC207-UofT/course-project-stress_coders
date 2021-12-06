package interfaceadapters.commands;

import entities.Interactable;
import usecases.Spin;

import java.util.HashMap;

/**
 * This is the command to spin, related to Spin.java
 */
public class SpinCommand extends Command {

    Spin spinAction;

    public SpinCommand() {
        this.spinAction = new Spin();
    }

    @Override
    public String execute(HashMap<String, Interactable> args) {
        return spinAction.spinAction(args);
    }

}