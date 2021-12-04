package interfaceadapters.commands;

import entities.Interactable;
import usecases.Command;
import usecases.TalkTo;

import java.util.HashMap;

/*
TalkTo command talks to a given interactable, that can be spoken to.
 */

public class TalkToCommand extends Command {
    TalkTo talkToAction;

    public TalkToCommand(){ this.talkToAction = new TalkTo(); }

    @Override
    public String execute(HashMap<String, Interactable> args){
        return this.talkToAction.talkToAction(args);
    }
}
