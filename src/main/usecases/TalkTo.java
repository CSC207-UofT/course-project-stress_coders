package usecases;

import entities.Interactable;
import entities.interfaces.Talkable;

import java.util.HashMap;

/**
 * Use case to talk to an object that one can talk to
 */
public class TalkTo {

    public String talkToAction(HashMap<String, Interactable> args) {
        /*
    Execute first by determining if the objs are of valid type with the correct arguments
    Call the listening method from the talkable receiver

    This command requires the receiver so the format would be
    talk_to: receiver=goblin1
    for example
     */
    String receiver = "receiver";
    if(args.get(receiver) instanceof Talkable) {
        Talkable listener = ((Talkable) args.get(receiver));
        return listener.listenAndRespond();
    } else {
        return "They cannot talk!";
    }
    }
}
