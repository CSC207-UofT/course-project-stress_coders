package usecases;

import entities.Interactable;
import entities.minigames.HorseRace;
import entities.characters.Player;

import java.util.HashMap;
import java.util.Scanner;

public class Race extends Command {
    /**
     * Execute the following to play the HorseRace or other Race interactables.
     *
     * @return a valid output string depending on the result of the user input or joust.
     */
    private Player player;

    @Override
    public String execute(HashMap<String, Interactable> args) {


        if (args.get("HorceRace") instanceof HorseRace) {
            HorseRace hr = ((HorseRace) args.get("HorseRace"));


            String message = "Are you sure you want to participate in a horse race?";

            System.out.println(message);

            System.out.println("Enter 'yes' to continue and anything else to forfeit the horse race!");
            Scanner lineIn = new Scanner(System.in);
            String choice = lineIn.nextLine();

            if (choice.equals("yes")) {

                System.out.println("The race has begun!");
                System.out.println("Press W key as many times and see if you win the race!");

                while (!hr.raceOver()) {
                    choice = lineIn.nextLine();
                    if (choice == "w") {
                        hr.incrementYah();
                    }

                }

                // now hr.raceOver();
                if (hr.playerWon()) {
                    return "Nice, you won the horse race";
                } else {
                    return "You lost the horse race to " + hr.getIdRaceWinner();
                }

            } else {
                return "You forfeitted the joust!";
            }


    }


        return "Invalid horse race, please pass in a valid horse race.";
    }


}
