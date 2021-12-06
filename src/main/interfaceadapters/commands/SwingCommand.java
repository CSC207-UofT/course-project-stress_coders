package interfaceadapters.commands;

import entities.Interactable;
import usecases.Swing;

import java.util.HashMap;

public class SwingCommand extends Command {

    final Swing swingAction;

    public SwingCommand() {
        this.swingAction = new Swing();
    }

    @Override
    public String execute(HashMap<String, Interactable> args) {
        return this.swingAction.shootAction(args);
    }
}