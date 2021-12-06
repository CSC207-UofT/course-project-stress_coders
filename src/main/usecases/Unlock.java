package usecases;

import entities.minigames.Door;
import entities.Interactable;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Use case to unlock a door (or anything unlockable)
 */
public class Unlock {
    public String unlockAction(HashMap<String, Interactable> args) {
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
