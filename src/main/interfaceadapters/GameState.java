package interfaceadapters;

import entities.Interactable;
import entities.Player;
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
    private ArrayList<Encounter> encounters = new ArrayList<>();
    private HashMap<String, Encounter> EncounterConversion = new HashMap<>();
    private List<Encounter> completedEncounters = new ArrayList<>();


    public GameState(Encounter[] encounters){
        loadEncounters(encounters);
        // will need to populate encounters
    }


    public String requestEncounter() {
        System.out.println("Please choose a quest:");
        for (Encounter e: encounters) {
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
        System.out.println(this.encounters.get(current_encounter).loadFirstInteractable());
        return "Good Luck!";
    }

    public void loadEncounter(Encounter encounter) {
        this.encounters.add(encounter);
        this.EncounterConversion.put(encounter.getName(), encounter);
    }

    public void loadEncounters(Encounter[] encounters) {
        for (Encounter e: encounters) {
            loadEncounter(e);
        }
    }

    public Encounter getCurrent_encounter(){
        return this.encounters.get(current_encounter);
    }

    public String callCommand(String input, HashMap<String, Interactable> args) {
        String s = encounters.get(current_encounter).progress(args, input) + "\n";
        if (encounters.get(current_encounter).isCompleted()) {
            this.completedEncounters.add(encounters.get(current_encounter));
            System.out.println(s);
            s = requestEncounter();
            return s;
        }
        return s;
    }

    public List<String> completedEncounters() {
        List<String> s = new ArrayList<>();
        for (Encounter e: this.completedEncounters) {
            s.add(e.getDetails());
        }
        return s;
    }

    public String getHelp(Player p) {
        return getCurrent_encounter().getHelp(p);
    }
}
