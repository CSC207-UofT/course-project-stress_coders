package usecases;

import Style.ColorConstants;
import entities.*;
import entities.characters.Character;
import entities.characters.Player;
import entities.interfaces.Spinnable;
import entities.interfaces.Target;
import interfaceadapters.commands.Command;

import java.util.*;

/*
Dictate the way a user can interact with the program at any given time. Done so by storing all objects
that can be interacted to represent a given scenario. I.e a fight scenario can be dictated by storing interactable
enemy objects in the encounter, a fishing scenario can be dictated by storing an interactable pond etc.

Also handle when the encounter is completed and how to add new objects into the encounter.
 */
public class Encounter {

    InteractablesManager interactablesManager = new InteractablesManager();
    private final String encounterName;
    private final String description;
    private boolean isCompleted;
    private int currInteractableIndex = -1;
    private final String initialText;
    private boolean doingGeneric = false;
    private int currGenericIndex = -1;


    public Encounter(String initialText, String name, String description){
        this.encounterName = name;
        this.description = description;
        this.initialText = initialText;
    }

    public String getDescription(){
        return this.description;
    }


    public ArrayList<Interactable> getProgression(){
        return this.interactablesManager.progression;
    }
    public InteractablesManager getInteractablesManager(){
        return this.interactablesManager;
    }

    public String getInitialText(){
        return this.initialText;
    }

    public boolean isDoingGeneric() {
        return doingGeneric;
    }

    public int getCurrGenericIndex() {
        return currGenericIndex;
    }

    public int getCurrInteractableIndex(){
        return this.currInteractableIndex;
    }


    public String getDetails() {
        return ColorConstants.getColorCode("BLUE") + this.encounterName + ColorConstants.getColorCode("RESET")+ " : " +
                ColorConstants.getColorCode("GREEN") + this.description + ColorConstants.getColorCode("RESET");
    }

    public String getName() {
        return this.encounterName;
    }

    /**
     * Loads the initial text for this encounter
     * @return String which is this encounter's initial text
     */
    public String loadInitial() {
        return this.initialText;
    }

    /**
     * This method is used to ask the user to choose their next desired interactable and to shift attributes
     * accordingly. It requests this whenever the encounter is first loaded and when an interactable is completed.
     * This method will run until a valid interaction is chosen, it will increment the main encounter index if one is
     * chosen or will select a generic otherwise.
     */
    public void requestInteractable() {
        boolean found = false;
        interactionDisplay();
        while (!found) {
            Scanner input = new Scanner(System.in);
            System.out.print("$ ");
            String nextInput = input.nextLine();

            if (interactablesManager.progression.contains(interactablesManager.objIDs.get(nextInput)) && interactablesManager.progression.get(currInteractableIndex+1).getId().equals(nextInput)) {
                found = true;
                System.out.println(mainMissionSelect());
            } else if (interactablesManager.genericPool.contains(interactablesManager.objIDs.get(nextInput))) {
                currGenericIndex = interactablesManager.genericPool.indexOf(interactablesManager.objIDs.get(nextInput));
                doingGeneric = true;
                System.out.println("Side Interaction started!");
                found = true;
                if (!interactablesManager.objIDs.get(nextInput).getInitialText().equals("")) {
                    System.out.println(interactablesManager.objIDs.get(nextInput).getInitialText());
                }
            }
            else if (interactablesManager.progression.contains(interactablesManager.objIDs.get(nextInput))) {
                System.out.println("When selecting Main mission, please select the next available one!");
            }
            else {
                System.out.println("Invalid selection, please choose another interaction");
            }
        }
    }

    /**
     * Used by requestInteractable() and is used to display all valid interactables to the user before they select
     */

    public void interactionDisplay() {
        System.out.println("======Main Missions:=====");
        int correctDisplay;
        if (currInteractableIndex == -1) {correctDisplay = 0;}
        else {correctDisplay = currInteractableIndex + 1;}
        for (int i = correctDisplay; i < interactablesManager.progression.size(); i++) {
            Interactable s = interactablesManager.progression.get(i);
            if (s.getInitialText().equals("")) {
                System.out.println(ColorConstants.getColorCode("BLUE") + s.getId() + ColorConstants.getColorCode("RESET"));
            }
            else {
                System.out.println(ColorConstants.getColorCode("BLUE") + s.getId() + ColorConstants.getColorCode("RESET")
                        + " : " + s.getInitialText());
            }
        }
        System.out.println("=====Side Interactions:=====");
        for (Interactable g: interactablesManager.genericPool) {
            if (g.getInitialText().equals("")) {
                System.out.println(ColorConstants.getColorCode("BLUE")+g.getId()+ColorConstants.getColorCode("RESET"));
            }
            else {
                System.out.println(ColorConstants.getColorCode("BLUE")+g.getId()+ColorConstants.getColorCode("RESET")
                        + ": " + g.getInitialText());
            }
        }
        System.out.println("Please select a mission or interaction by entering its name marked in " +
                ColorConstants.getColorCode("BLUE") + "blue" + ColorConstants.getColorCode("RESET") +
                ", if selecting a mission, make sure you choose" +
                " the first available one!");
    }

    /**
     * Used if a main encounter is selected in the requestInteractable section, it will increment the current encounter
     * index and then return initial test of that interactable to be displayed by requestInteractable()
     * @return String which is the initial text for the chosen interactable
     */
    public String mainMissionSelect() {
        currInteractableIndex++;
        return "Main mission started, enjoy!";

    }

    /**
     * This method is the one used by game state, it directly uses user input to progress through an interactable and
     * the entire encounter and marks the encounter as completed when needed, it also handles when to request
     * interactables
     * @return A string representing what the command execution returns (fail or not fail) and the progression of this
     * encounter
     */
    public String progress(String commandResult) {
        // WE may need to add some sort of checking to see if the interactable is valid for this encounter
        // as of now, this method doesn't do this so the command will run but no progress will be made
        if (doingGeneric) {
            if (interactablesManager.objIDs.get(interactablesManager.genericPool.get(currGenericIndex).getId()).isCompleted()) {
                doingGeneric = false;
                currGenericIndex = -1;
                requestInteractable();
            }
            return commandResult;
        }
        if (interactablesManager.progression.get(currInteractableIndex).isCompleted()) {
            System.out.println(commandResult);
            if (currInteractableIndex == interactablesManager.progression.size()-1) {
                this.isCompleted = true;
                return "Encounter completed, well done!";
            }
            requestInteractable();
            return interactablesManager.progression.get(currInteractableIndex).getInitialText();
        }
        return commandResult;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Provides specific help for the player based off of the interactable they are currently in
     * @param player Player object that is used throughout program
     * @return String representing the specific help for this current situation
     */
    public String getHelp(Player player) {
        System.out.println("All commands will be of the form -- command: [param_type]=[param_name]");
        if (doingGeneric) {
            if (this.interactablesManager.genericPool.get(currGenericIndex) instanceof Spinnable) {
                System.out.println("You currently have " + player.getWallet() + " geld");
            }
            else if (this.interactablesManager.genericPool.get(currGenericIndex) instanceof Target) {
                String s = "Your current weapon is " + player.getCurrentWeapon().getId() + "\n";
                return s+ "Current command(s) - " + player.getCurrentWeapon().getHelp();
            }
            return "Current command(s) - " + this.interactablesManager.genericPool.get(currGenericIndex).getHelp();
        }
        if (this.interactablesManager.progression.get(currInteractableIndex) instanceof Target) {
            String s = "Your current weapon is " + player.getCurrentWeapon().getId() + "\n";
            return s+ "Current command(s) - " + player.getCurrentWeapon().getHelp();
        }
        return "Current command(s) - " + this.interactablesManager.progression.get(currInteractableIndex).getHelp();
    }

    public ArrayList<Interactable> getGenericPool(){
        return this.interactablesManager.genericPool;
    }

    public void setCompleted(Boolean completed){
        this.isCompleted = completed;
    }

    public void setCurrInteractableIndex(int index){
        this.currInteractableIndex = index;
    }

    public void setDoingGeneric(boolean doingGeneric){
        this.doingGeneric = doingGeneric;
    }

    public void setCurrGenericIndex(int index){
        this.currGenericIndex = index;
    }

    public void setProgression(ArrayList<Interactable> progression){
        this.interactablesManager.progression = progression;
    }

    public void setGenericPool(ArrayList<Interactable> genericPool) {
        this.interactablesManager.genericPool = genericPool;
    }
}