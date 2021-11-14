package usecases;

import entities.*;
import entities.Character;
import entities.interfaces.Spinnable;
import interfaceadapters.IDreader;

import java.util.*;

/*
Dictate the way a user can interact with the program at any given time. Done so by storing all objects
that can be interacted to represent a given scenario. I.e a fight scenario can be dictated by storing interactable
enemy objects in the encounter, a fishing scenario can be dictated by storing an interactable pond etc.

Also handle when the encounter is completed and how to add new objects into the encounter.
 */
public class Encounter {

    //Maps Interactable ID to the object itself, 'key 1': KeyObject
    private String encounterName;
    private String description;
    private HashMap<String, Interactable> objIDs = new HashMap<String, Interactable>();
    private boolean isCompleted;
    private ArrayList<Interactable> progression = new ArrayList<>();
    private int currInteractableIndex = -1;
    private String initialText;
    private ArrayList<Interactable> genericPool = new ArrayList<>();
    private boolean doingGeneric = false;
    private int currGenericIndex = -1;


    public Encounter(String initialText, String name, String description){
        this.encounterName = name;
        this.description = description;
        this.initialText = initialText;
    }

    /**
     * Loads a list of interactables into the encounter
     * @param interactables the list of interactables to add to this encounter, should be ordered and only main
     * interactions should be included
     */
    public void loadInteractables(List<Interactable> interactables) {
        for (Interactable interactable : interactables){
            addObj(interactable);
        }
    }

    /**
     * Returns a list of interactables that can be done in this encounter
     * @returns a list of the interactables as a string of their id's split by \n
     */
    public String listInteractables() {
        String out = "";
        for (String key : this.objIDs.keySet()) {
            out += objIDs.get(key).getId() + "\n";
        }
        return out;
    }

    public String getDetails() {
        return this.encounterName + " : " + this.description;
    }

    public String getName() {
        return this.encounterName;
    }



    public void addGeneric(Interactable generic) {
        if (generic instanceof Item) {
            addAdjective(generic);
            return;
        }

        if(this.objIDs.containsKey(generic.getId())){
            addAdjective(generic);
        }
        this.genericPool.add(generic);
        this.objIDs.put(generic.getId() ,generic);
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

        if (progression.contains(objIDs.get(nextInput)) && progression.get(currInteractableIndex+1).getId().equals(nextInput)) {
            found = true;
            System.out.println(mainMissionSelect());
        } else if (genericPool.contains(objIDs.get(nextInput))) {
            currGenericIndex = genericPool.indexOf(objIDs.get(nextInput));
            doingGeneric = true;
            System.out.println("Side Interaction started!");
            found = true;
            if (!objIDs.get(nextInput).getInitialText().equals("")) {
                System.out.println(objIDs.get(nextInput).getInitialText());
            }
        }
        else if (progression.contains(objIDs.get(nextInput))) {
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
        System.out.println("Main Missions:");
        int correctDisplay;
        if (currInteractableIndex == -1) {correctDisplay = 0;}
        else {correctDisplay = currInteractableIndex + 1;}
        for (int i = correctDisplay; i < progression.size(); i++) {
            Interactable s = progression.get(i);
            System.out.println(s.getId() + " : " +  s.getInitialText());
        }
        System.out.println("Side Interactions:");
        for (Interactable g: genericPool) {
            System.out.println(g.getId() + ": " + g.getInitialText());
        }
        System.out.println("Please select a mission or interaction, if selecting a mission, make sure you choose" +
                "the first available one!");
    }

    /**
     * Used if a main encounter is selected in the requestInteractable section, it will increment the current encounter
     * index and then return initial test of that interactable to be displayed by requestInteractable()
     * @return String which is the initial text for the chosen interactable
     */
    public String mainMissionSelect() {
        currInteractableIndex++;
        System.out.println("Main mission started, enjoy!");
        return progression.get(currInteractableIndex).getInitialText();
    }

    /**
     * This method is the one used by game state, it directly uses user input to progress through an interactable and
     * the entire encounter and marks the encounter as completed when needed, it also handles when to request
     * interactables
     * @param userInput This is the format for commands and their related objects in this program
     * @param userCommand the command that the user has chosen to be executed, this method will call on the command
     *                    execution which will check if it's a valid interaction too
     * @return A string representing what the command execution returns (fail or not fail) and the progression of this
     * encounter
     */
    public String progress(HashMap<String, Interactable> userInput, String userCommand) {
        // WE may need to add some sort of checking to see if the interactable is valid for this encounter
        // as of now, this method doesn't do this so the command will run but no progress will be made
        CommandConstants c = new CommandConstants();
        Command needed = c.getCommand(userCommand);
        String s = needed.execute(userInput);
        if (doingGeneric) {
            if (objIDs.get(genericPool.get(currGenericIndex).getId()).isCompleted()) {
                doingGeneric = false;
                currGenericIndex = -1;
                requestInteractable();
            }
            return s;
        }
        if (progression.get(currInteractableIndex).isCompleted()) {
            System.out.println("Here");
            System.out.println(s);
            if (currInteractableIndex == progression.size()-1) {
                this.isCompleted = true;
                return "Encounter completed, well done!";
            }
            requestInteractable();
            return progression.get(currInteractableIndex).getInitialText();
        }
        return s;
    }

    // If object has identical ID either add a number to the end or add some adjective at the beginning
    // We can have a list of ObjectAdjectives.txt, so like Big, red etc. So if there are 2 keys, one can be Big the other red etc.
    // This method only adds main interactables
    public void addObj(Interactable interactable){
        if (interactable instanceof Item) {
            addAdjective(interactable);
            return;
        }

        if (this.objIDs.containsKey(interactable.getId())){
            addAdjective(interactable);
        }
        this.objIDs.put(interactable.getId(), interactable);
        this.progression.add(interactable);
    }

    public boolean containsObj(Interactable item) {
        return this.objIDs.containsKey(item.getId());
    }

    public void addAdjective(Interactable interactable){
        String id = interactable.getId();
        String originalId = interactable.getId();

        Random random = new Random();

        String[] charKeySet = IDreader.CharAdjectives.keySet().toArray(new String[0]);
        String[] objKeySet = IDreader.ObjAdjectives.keySet().toArray(new String[0]);
        int index = 0;
        while(this.objIDs.containsKey(id)){
            if(interactable instanceof Character) {
                index = random.nextInt(charKeySet.length);
                id = charKeySet[index] + " " + originalId;
            } else {
                index = random.nextInt(objKeySet.length);
                id = objKeySet[index] + " " + originalId;
            }
        }

        if(interactable instanceof Character){
            ((Character) interactable).addModifier(IDreader.CharAdjectives.get(charKeySet[index]));
        } else {
            for(Variable var : interactable.getProperties().values()){
                float modified_value = var.getInteger() * IDreader.ObjAdjectives.get(objKeySet[index]);
                var.setInteger((int) modified_value);
            }
        }

        interactable.setId(id);
    }

    public Interactable getFromID(String ID){
        return objIDs.get(ID);
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
        if (doingGeneric) {
            if (this.genericPool.get(currGenericIndex) instanceof Spinnable) {
                System.out.println("You currently have " + player.getWallet() + " geld");
            }
            return this.genericPool.get(currGenericIndex).getHelp();
        }
        if (this.progression.get(currInteractableIndex) instanceof Enemy) {
            System.out.println("Your current weapon is " + player.getCurrentWeapon().getId());
            return player.getCurrentWeapon().getHelp();
        }
        return this.progression.get(currInteractableIndex).getHelp();
    }

    public ArrayList<Interactable> getGenericPool(){
        return this.genericPool;
    }

}
