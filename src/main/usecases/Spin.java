package usecases;

import entities.*;

import java.util.HashMap;
import java.util.Scanner;

public class Spin extends Command {

    @Override
    public String execute(HashMap<String, Interactable> args) {
        if (args.get("spin") instanceof MysteryBox) {
            MysteryBox m = ((MysteryBox) args.get("spin"));
            Weapon w = m.spin();

            if (w instanceof Unafforable) {
                return "Sorry, you cannot afford to hit the mystery box, come back later!";
            }
            String message = "Do you want to switch your current weapon to weapon " + w.getId() +
                    "with damage" + w.getDamage();

            System.out.println(message);
            System.out.println("Enter 'yes' to pick up weapon and anything else to not pick up!");
            Scanner lineIn = new Scanner(System.in);
            String choice = lineIn.nextLine();
            if (choice.equals("yes")) {
                m.changePlayerWeapon(w);
                return "Nice, you picked up " + w.getId() + ", enjoy!";
            }
            return "Seems you like your weapon, have at it!";
        }
        return "Invalid mystery box, please pass in a valid mystery box.";
    }
}
