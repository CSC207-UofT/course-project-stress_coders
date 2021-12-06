package interfaceadapters.commands;

import entities.Interactable;
import usecases.Hint;

import java.util.HashMap;

public class HintCommand extends Command {
    /**
     * gives hint to help open door
     */
    Hint hint;

    public HintCommand(){
        this.hint = new Hint();
    }
    @Override
    public String execute(HashMap<String, Interactable> args) {
        return hint.giveHint(args);
    }
}
