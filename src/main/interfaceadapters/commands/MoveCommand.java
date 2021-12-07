package interfaceadapters.commands;

import entities.Interactable;
import usecases.Move;

import java.util.HashMap;

public class MoveCommand extends Command{
    final Move move;

    public MoveCommand() {this.move = new Move(); }

    @Override
    public String execute(HashMap<String, Interactable> args) {
        return this.move.moveAction(args);
    }
}
