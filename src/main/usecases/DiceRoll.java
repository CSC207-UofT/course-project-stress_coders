package usecases;

import entities.Interactable;
import entities.minigames.Catapult;
import entities.minigames.Teetotum;

import java.util.HashMap;
import java.util.Scanner;

public class DiceRoll {

    public String playInLaunch(HashMap<String, Interactable> args) {

        if (args.get("dice") instanceof Teetotum) {
            Teetotum tm = ((Teetotum) args.get("dice"));


            String message = "Are you sure you want to participate in the teetotum game?";

            System.out.println(message);

            System.out.println("Enter 'yes' to continue and anything else to forfeit the teetotum game!");

            Scanner lineIn = new Scanner(System.in);
            String choice = lineIn.nextLine();

            if (choice.equals("yes")) {
                tm.roll();
                System.out.println("The catapult launch has begun!");

                // now hr.raceOver();
                if (tm.rollOver()) {
                    return "Nice, you succesfully launched the rock on top of the enemy and won the catapult mini-game!";
                } else {
                    return "You lost the catapult game";
                }
            }


            return "You forfeitted the joust!";
        }

        return "Invalid diceRollable, please pass in a valid dice.";

    }
}
