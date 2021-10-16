package gamecontroller;

import encounter.Encounter;
import weaponiteminterfaces.ThrowableObject;
import weapons.*;
import characters.*;
import java.util.*;
import items.*;

public class GameCompiler {
    int currentEncounter = 0;
    Player player;
    ArrayList<Encounter> encounters;
    boolean canProceed;

    public GameCompiler() {
        this.canProceed = true;
        // This will point to first encounter at some point but for now it's a demo
        Set<String> commands = new HashSet<>();
        Weapon axe = new Axe("Molag Bal", 25);
        List<Item> fakeItems = new ArrayList<>();
        this.player = new Player(fakeItems, 500, axe);
        this.player.setWeapon(axe);
        commands.add("pick up");
        commands.add("stab");
        commands.add("block");
        commands.add("throw");
        this.encounters = new ArrayList<>();
        Encounter first_encounter = new Encounter("Do you want to kill the monster?", commands);
        this.encounters.add(first_encounter);

        Encounter next_encounter = new Encounter("Do you want to pickup this axe?", commands);
        Encounter last_encounter = new Encounter("Oh no a new dragon appears, what do you do?", commands);
        this.encounters.add(next_encounter);
        this.encounters.add(last_encounter);
    }
    public String getDialogue() {
        if (currentEncounter >= this.encounters.size()) {
            return "=== THANK YOU FOR PLAYING ===";
        }
        Encounter current = this.encounters.get(currentEncounter);
        return current.getDialogue();
    }
    /**
     * The run method first checks if the command is valid for that encounter, if it is then it prints the prompt for
     * that command e.g. pick up sword - You've picked up sword
     * It then switches encounter to its next one or switches quest is the encounter is done and stores the info
     * throughout
     */

    public String run(String command) {
        Weapon axe = new Axe("Molag Bal", 25);
        if (currentEncounter >= this.encounters.size()) {
            return "Game is over, please exit with the 'exit' command!";
        }
        Encounter current = this.encounters.get(currentEncounter);
        if (!current.isValidCommand(command)) {
            return "This is not a valid command for this encounter, try again.";
        }
        // Hard coded actions for now
        Map<String, String> commandReturns = new HashMap<>();
        commandReturns.put("pick up", "You've picked up the sword, use it to your will!");
        commandReturns.put("stab", "You've shanked the monster, smoke his ass!");
        commandReturns.put("block", "You've outplayed the monster and blocked his attack!");
        commandReturns.put("throw", "Nice hit!");
        if (command.equals("stab") && player.getWeapon() == null) {
            return "Sorry, mate you've got no weapon";
        }
        if (command.equals("pick up") && player.getWeapon() != null) {
            return "Sorry, inventory is full, cannot pick up!";
        }
        if (command.equals("pick up")) {
            if (canProceed) {
                player.setWeapon(player.getPreviousWeapon());
                System.out.println("You've picked up your axe! Chop his legs!");
            }
            else {
                player.setWeapon(player.getPreviousWeapon());
                return "You've picked up your axe! Chop his legs!";
            }
        }
        if (command.equals("throw")) {
            if (!(player.getWeapon() instanceof ThrowableObject)) {
                return "Not a throwable weapon, please choose another action.";
            }
            String event = ((ThrowableObject) player.getWeapon()).throwObj(this.player);
            if (event.equals("You hit your target")) {
                this.canProceed = true;
                System.out.println(event);
                System.out.println("Remember to pick up your weapon!");
            }
            else {
                this.canProceed = false;
                System.out.println(event);
                return "Please pick up and try again!";
            }
        }

        currentEncounter++;


        return commandReturns.get(command);
    }
}