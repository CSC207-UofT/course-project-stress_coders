package usecases;

import entities.Door;
import entities.Interactable;

import java.util.HashMap;
import java.util.Scanner;

public class Unlock extends Command{

    /*
    User input would be like "unlock: door=door1"
    This command would require input of the format {"door": door1}
     */
    @Override
    public String execute(HashMap<String, Interactable> args) {
        if (args.get("door") instanceof Door) {
            Door d = ((Door) args.get("door"));
            Scanner lineIn = new Scanner(System.in);
            System.out.println("What password would you like to try?");  // Get the user's message
            String password = lineIn.nextLine();
            return d.userAttempt(password);
        }
        return "Invalid door, please pass in a valid door.";
    }
}
