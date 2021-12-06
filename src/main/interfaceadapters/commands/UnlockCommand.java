package interfaceadapters.commands;

import entities.Interactable;
import usecases.Unlock;

import java.util.HashMap;

public class UnlockCommand extends Command {

    final Unlock unlockAction;

    public UnlockCommand() {
        this.unlockAction = new Unlock();
    }

    /*
    User input would be like "unlock: door=door1"
    This command would require input of the format {"door": door1}
     */
    @Override
    public String execute(HashMap<String, Interactable> args) {
        return this.unlockAction.unlockAction(args);
    }
}
