package usecases;

import entities.*;

import java.util.HashMap;
import java.util.Scanner;

public class Spin extends Command {

    @Override
    public String execute(HashMap<String, Interactable> args) {
        if (args.get("box") instanceof MysteryBox) {
            MysteryBox m = ((MysteryBox) args.get("box"));
            Weapon w = m.spin();

            if (w instanceof Unafforable) {
                return "Sorry, you cannot afford to hit the mystery box, come back later!";
            }
            String message = "Do you want to switch your current weapon to weapon " + w.getId() +
                    "with damage" + ((Integer) w.getDamage()).toString();

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
        else if (args.get("box") instanceof PotionDispenser) {
            PotionDispenser p = ((PotionDispenser) args.get("box"));
            Potion w = p.spin();

            if (w instanceof UnusablePotion) {
                return "Sorry, you cannot afford to hit the potion dispenser, come back later!";
            }
            String message = "Do you want to pick up potion " + w.getId() +
                    "with restoration " + ((Integer) w.restorationValue()).toString();

            System.out.println(message);
            System.out.println("Enter 'yes' to pick up potion and anything else to not pick up!");
            Scanner lineIn = new Scanner(System.in);
            String choice = lineIn.nextLine();
            if (choice.equals("yes")) {
                p.addPotionToPlayer(w);
                return "Nice, you picked up " + w.getId() + ", enjoy!";
            }
            return "Seems you like your weapon, have at it!";
        }
        return "Invalid box, please pass in a valid box.";
    }
}