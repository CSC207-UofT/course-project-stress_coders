package usecases;

import entities.*;
import entities.characters.Character;
import entities.characters.Enemy;
import entities.characters.Player;
import entities.minigames.Joust;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.Optional;

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
            String check = lineIn.nextLine();


            if (!Objects.equals(j.playerWon(check), Optional.empty())) {
                Optional<Character> result = j.playerWon(check);
                // this must not be Optional.empty, so its either Enemy or Player

                if (result.get().equals(player)) {
                    return "Nice, you won the joust and got " + (j.getValueDefeated()) + " !";
                }
                else { // otherwise it must be the Enemy, since its not Optional.empty
                    return "You lost the joust to " + ((Enemy) result.get()).getId() +" and died!!";

                }

            } // otherwise j.playerWon(check) is Optional.empty()

            return "You forfeitted the joust!";

        }

        return "Invalid joust, please pass in a valid joust.";


    }
}
