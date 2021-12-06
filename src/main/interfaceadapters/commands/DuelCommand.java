package interfaceadapters.commands;

import entities.*;
import usecases.Duel;

import java.util.HashMap;


public class DuelCommand extends Command {
    /**
     * allows to play the Joust or other Duel interactables.
     *
     */
    Duel duel;

    public DuelCommand() {
        this.duel = new Duel();
    }

    @Override
    public String execute(HashMap<String, Interactable> args) {
        return this.duel.DuelCharacter(args);
    }
}
