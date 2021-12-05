package usecases;

import entities.Interactable;
import entities.characters.Player;
import entities.minigames.Catapult;

import java.util.HashMap;
import java.util.Scanner;

public class Launch {

    private Player player;

    /**
     * plays the Catapult or other Launch interactables.
     *
     * @return a valid output string depending on the result of the user input or joust.
     */

    public String playInLaunch(HashMap<String, Interactable> args) {
        if (args.get("catapult") instanceof Catapult) {
            Catapult ct = ((Catapult) args.get("catapult"));


            String message = "Are you sure you want to participate in the catapult launch?";

            System.out.println(message);

            System.out.println("Enter 'yes' to continue and anything else to forfeit the catapult launch!");
            Scanner lineIn = new Scanner(System.in);
            String choice = lineIn.nextLine();

            if (choice.equals("yes")) {

                System.out.println("The catapult launch has begun!");

                // now hr.raceOver();
                if (ct.launch()) {
                    return "Nice, you succesfully launched the rock on top of the enemy and won the catapult mini-game!";
                } else {
                    return "You lost the catapult game";
                }
            }

            return "You forfeited the catapult!";
        }

        return "Invalid catipult, please pass in a valid catapult.";

    }
}