package interfaceadapters;

import entities.Interactable;
import usecases.*;

import java.util.*;

/*
Singleton
Store the current state of encounters: The current encounter, the map of available encounters, the
completed encounters and the required encounters.

Also handle moving between encounters.
Potentially handle saving encounters
 */
public class GameState {

    private int current_encounter;
    private final ArrayList<Encounter> encounters = new ArrayList<>();
    private final HashMap<String, Encounter> EncounterConversion = new HashMap<>();
    private final List<Encounter> completedEncounters = new ArrayList<>();
    private PlayerManager playerState;


    public GameState(Encounter[] encounters){
        loadEncounters(encounters);
        // will need to populate encounters
    }

    public void setPlayerManager(PlayerManager p) {
        this.playerState = p;
    }
    /**
     * The function prints all available encounters, line by line using getDetails()
     * The user is prompted to choose an encounter and the prompt will not pass until a valid quest is parsed
     * @return Good Luck! but will print user quest selections in the process
     */
    public String requestEncounter() {
        System.out.println("Please choose a quest:");
        System.out.println("==TO DO==");
        for (Encounter e: encounters) {
            if (!completedEncounters.contains(e)) {
                String enc = e.getDetails();
                System.out.println(enc);
            }
        }
        System.out.println("==COMPLETED==");
        for (Encounter e: completedEncounters) {
            String enc = e.getDetails();
            System.out.println(enc);
        }

        Scanner input = new Scanner(System.in);
        System.out.print("$ ");
        String nextInput = input.nextLine();
        Encounter needed = EncounterConversion.get(nextInput);
        int found = this.encounters.indexOf(needed);
        while (found == -1) {
            System.out.println("No quest found by that name");
            Scanner input2 = new Scanner(System.in);
            System.out.print("$ ");
            String nextInput2 = input2.nextLine();
            Encounter needed2 = EncounterConversion.get(nextInput2);
            found = this.encounters.indexOf(needed2);
        }
        this.current_encounter = found;
        System.out.println("Quest Selected!");
        System.out.println(this.encounters.get(current_encounter).loadInitial());
        this.encounters.get(current_encounter).requestInteractable();
        return "Good Luck!";
    }

    /**
     * load a single encounter into the game
     * @param encounter a single Encounter Object
     */
    public void loadEncounter(Encounter encounter) {
        this.encounters.add(encounter);
        this.EncounterConversion.put(encounter.getName(), encounter);
    }

    /**
     * load multiple encounters into the game
     * @param encounters multiple Encounter Objects
     */
    public void loadEncounters(Encounter[] encounters) {
        for (Encounter e: encounters) {
            loadEncounter(e);
        }
    }

    /**
     * getter method for the current encounter
     * @return Encounter Object (current encounter)
     */
    public Encounter getCurrent_encounter(){
        return this.encounters.get(current_encounter);
    }

    /**
     * getter method for the current encounter
     * @return Encounter Object (current encounter)
     */
    public String callCommand(String input, HashMap<String, Interactable> args) {
        String s = encounters.get(current_encounter).progress(args, input) ;
        if (encounters.get(current_encounter).isCompleted()) {
            this.completedEncounters.add(encounters.get(current_encounter));
            System.out.println(s);
            if (completedEncounters.size() == encounters.size()) {
                System.out.println("You've successfully completed all encounters, well done padawan!");
            }
            s = requestEncounter();
            return s;
        }
        return s;
    }

    /**
     * This method returns a list of completed encounters details which is needed for special command 'progress'
     * @return List of string where each string are the details of a completed encounter provided by Encounter's
     * getDetails() method
     */
    public List<String> completedEncounters() {
        List<String> s = new ArrayList<>();
        for (Encounter e: this.completedEncounters) {
            s.add(e.getDetails());
        }
        return s;
    }

    /**
     * Provides the user with personalised help for the given encounter
     * @return String which is the help message for the user, will be provided by the encounter they are currently in
     */
    public String getHelp() {
        return getCurrent_encounter().getHelp(playerState.getPlayer());
    }

    public Interactable getFromID(String s) {
        if (playerState.getPlayer().getCurrentWeapon().getId().equals(s)) {
            return playerState.getPlayer().getCurrentWeapon();
        }
        return this.encounters.get(current_encounter).getFromID(s);
    }
}
