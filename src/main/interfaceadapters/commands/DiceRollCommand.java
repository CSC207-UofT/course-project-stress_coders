package interfaceadapters.commands;

import entities.Interactable;

import usecases.DiceRoll;

import java.util.HashMap;

public class DiceRollCommand extends Command {

    final DiceRoll diceRoll;

    public DiceRollCommand() {
        this.diceRoll = new DiceRoll();
    }


    @Override
    public String execute(HashMap<String, Interactable> args) {
        return null;
    }
}
