package usecases;

import entities.*;
import entities.Character;
import entities.Enemy;

import java.util.HashMap;
import java.util.Scanner;

public class Duel extends Command {

    private Player player;

    @Override
    public String execute(HashMap<String, Interactable> args) {

        /**
         * Execute the following to play the Joust or other Duel interactables.
         *
         * @return a valid output string depending on the result of the user input or joust.
         */

        if (args.get("joust") instanceof Joust) {
            Joust j = ((Joust) args.get("joust"));

            String message = "Are you sure you want to Joust?";

            System.out.println(message);

            System.out.println("Enter 'yes' to continue  and anything else to forfeit the joust!");
            Scanner lineIn = new Scanner(System.in);
            String choice = lineIn.nextLine();


            if (choice.equals("yes")) {
                Character result = j.playerWon();
                if (result == player) {
                    return "Nice, you won the joust and got " + j.getValueDefeated() + " !";
                }
                else {
                    return "You lost the joust to " + ((Enemy) result).getId() +" and died!!";

                }

            }

            return "You forfeitted the joust!";

        }

        return "Invalid joust, please pass in a valid joust.";


    }
}
