package usecases;

import entities.Character;
import entities.Interactable;
import entities.Joust;

import java.util.HashMap;
import java.util.Scanner;

public class Duel extends Command {

    @Override
    public String execute(HashMap<String, Interactable> args) {

        if (args.get("joust") instanceof Joust) {
            Joust j = ((Joust) args.get("joust"));

            String message = "Are you sure you want to Joust?";

            System.out.println(message);

            System.out.println("Enter 'yes' to continue  and anything else to forfeit the joust!");
            Scanner lineIn = new Scanner(System.in);
            String choice = lineIn.nextLine();
            if (choice.equals("yes")) {
                boolean result = j.playerWon();
                if (result == true) {
                    return "Nice, you won the joust!";
                }
                else {
                    return "You lost the joust and died!!";
                }

            }
            return "You forfeitted the joust!";

        }

        return "Invalid joust, please pass in a valid joust.";


    }
}
