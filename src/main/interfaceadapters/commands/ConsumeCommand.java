package interfaceadapters.commands;

import entities.Interactable;
import usecases.Consume;

import java.util.HashMap;

/**
 * Consume a consumable. This is a special command.
 */
public class ConsumeCommand extends Command {
    final Consume consume;

    public ConsumeCommand(){
        this.consume = new Consume();
    }
    @Override
    public String execute(HashMap<String, Interactable> args) {
        return this.consume.consumeFood(args);
    }
}
