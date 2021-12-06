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
                System.out.println("The teetotum game has begun!");

                // now hr.raceOver();
                if (tm.rollOver()) {
                    return "Nice, you won the teetotum mini-game!";
                } else {
                    return "You lost the teetotum mini-game";
                }
            }


            return "You forfeitted the mini-game!";
        }

        return "Invalid diceRollable, please pass in a valid dice.";

    }
}
