package gamecontroller;

import encounter.Encounter;

import java.util.*;

public class GameCompiler {
    int currentEncounter = 0;
    ArrayList<Encounter> encounters;
    public GameCompiler() {
        // This will point to first encounter at some point but for now it's a demo
        Set<String> commands = new HashSet<>();
        commands.add("Pick Up");
        commands.add("Stab");
        commands.add("Block");
        this.encounters = new ArrayList<>();
        Encounter firstEncounter = new Encounter("Do you want to pickup this axe?", commands);
        this.encounters.add(firstEncounter);
        Encounter next_encounter = new Encounter("Do you want to stab the monster?", commands);
        this.encounters.add(next_encounter);
    }
    public String getDialogue() {
        if (currentEncounter >= this.encounters.size()) {
            return "=== THANK YOU FOR PLAYING ===";
        }
        Encounter current = this.encounters.get(currentEncounter);
        return current.dialogue_getter();
    }
    /**
     * The run method first checks if the command is valid for that encounter, if it is then it prints the prompt for
     * that command e.g. pick up sword - You've picked up sword
     * It then switches encounter to its next one or switches quest is the encounter is done and stores the info
     * throughout
     */

    public String run(String command) {
        if (currentEncounter >= this.encounters.size()) {
            return "Game is over, please exit with the 'exit' command!";
        }
        Encounter current = this.encounters.get(currentEncounter);
        if (!current.isValidCommand(command)) {
            return "This is not a valid command for this encounter, try again.";
        }
        // Hard coded actions for now
        Map<String, String> commandReturns = new HashMap<>();
        commandReturns.put("Pick Up", "You've picked up the sword, use it to your will!");
        commandReturns.put("Stab", "You've shanked the monster, smoke his ass!");
        commandReturns.put("Block", "You've outplayed the monster and blocked his attack!");

        currentEncounter++;


        return commandReturns.get(command);
    }
}