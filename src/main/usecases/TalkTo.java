package usecases;

import entities.*;
import entities.interfaces.*;
import entities.interfaces.Throwable;
import entities.Interactable;

import java.util.HashMap;
import java.util.Scanner;

/*
TalkTo command talks to a given interactable, that can be spoken to.
 */

public class TalkTo extends Command {

    /*
    Execute first by determining if the objs are of valid type with the correct arguments
    Call the listening method from the talkable receiver

    This command requires the receiver so the format would be
    talk_to: receiver=goblin1
    for example
     */
    @Override
    public String execute(HashMap<String, Interactable> args) {
        String receiver = "target";
        if(args.get(receiver) instanceof Talkable) {
            Talkable listener = ((Talkable) args.get(receiver));
            Scanner lineIn = new Scanner(System.in);
            System.out.println("What would you like to say?");  // Get the user's message
            String message = lineIn.nextLine();
            return listener.listenAndRespond(message);
        } else {
            return "They cannot talk!";
        }
    }
}
