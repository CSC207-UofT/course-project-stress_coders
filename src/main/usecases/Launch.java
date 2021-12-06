package usecases;

import entities.Interactable;
import entities.characters.Player;
import entities.minigames.Catapult;
import entities.minigames.Fishing;
import entities.minigames.HorseShoe;
import entities.minigames.PebbleSkip;

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
        if (args.get("launchable") instanceof Catapult) {
            Catapult ct = ((Catapult) args.get("launchable"));


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
        } else if (args.get("launchable") instanceof PebbleSkip) {
            PebbleSkip pb = ((PebbleSkip) args.get("launchable"));


            String message = "Are you sure you want to participate in the pebble skipping minigame?";

            System.out.println(message);

            System.out.println("Enter 'yes' to continue and anything else to forfeit the pebble skip");
            Scanner lineIn = new Scanner(System.in);
            String choice = lineIn.nextLine();

            if (choice.equals("yes")) {

                System.out.println("The pebble skip has begun!");

                // now hr.raceOver();
                if (pb.launch()) {
                    return "Nice, you succesfully skipped the pebble on top of the water" + pb.getNumSkips() + "!";
                } else {
                    return "You failed to skip the pebble on top of the water!";
                }
            }

            return "You forfeited the pebble skip!";
        } else if (args.get("launchable") instanceof Fishing) {
            Fishing fh = ((Fishing) args.get("launchable"));


            String message = "Are you sure you want to participate in fishing?";

            System.out.println(message);

            System.out.println("Enter 'yes' to continue and anything else to forfeit fishing");
            Scanner lineIn = new Scanner(System.in);
            String choice = lineIn.nextLine();

            if (choice.equals("yes")) {

                System.out.println("The pebble skip has begun!");
                System.out.println("Enter the distance of the fishing rod in the water, between 0 and 9");
                Scanner distanceLineIn = new Scanner(System.in);
                String distanceChoice = lineIn.nextLine();

                fh.setPullBackDistance(Integer.parseInt(distanceChoice));

                // now hr.raceOver();
                if (fh.launch()) {
                    return "Nice, you succesfully caught a fish in the water!";
                } else {
                    return "You failed to catch a fish in the water!";
                }
            }

            return "You forfeited from fishing!";
        }  else if (args.get("launchable") instanceof HorseShoe) {
            HorseShoe hs = ((HorseShoe) args.get("launchable"));


            String message = "Are you sure you want to participate in horse shoe toss?";

            System.out.println(message);

            System.out.println("Enter 'yes' to continue and anything else to forfeit the horse shoe toss");
            Scanner lineIn = new Scanner(System.in);
            String choice = lineIn.nextLine();

            if (choice.equals("yes")) {

                System.out.println("The pebble skip has begun!");

                hs.newToss();

                System.out.println("You now have " + hs.getPoints() + " points");

                System.out.println("Do you want to keep tossing? Enter 'no' to quit tossing.");
                Scanner tossLineIn = new Scanner(System.in);
                String tossChoice = lineIn.nextLine();


                while(!tossChoice.equals("no")) {
                    hs.newToss();
                    System.out.println("Your points are now " + hs.getPoints() + " points");
                }

                if (hs.launch()) {
                    return "Nice, you succesfully won the horse shoe toss!";
                } else {
                    return "You failed the horse shoe toss minigame!";
                }
            }

            return "You forfeited from the horse shoe toss!";
        }

        return "Invalid launchable, please pass in a valid launchable.";

    }
}