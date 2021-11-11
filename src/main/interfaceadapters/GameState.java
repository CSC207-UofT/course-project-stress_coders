package interfaceadapters;

import usecases.*;

import java.util.HashSet;

/*
Singleton
Store the current state of encounters: The current encounter, the map of available encounters, the
completed encounters and the required encounters.

Also handle moving between encounters.
Potentially handle saving encounters
 */
public class GameState {

    private Encounter current_encounter = new Encounter();
    public static HashSet<String> ObjAdjectives = new HashSet<String>();
    public static HashSet<String> CharAdjectives = new HashSet<String>();

    public GameState(){

    }

    public Encounter getCurrent_encounter(){
        return this.current_encounter;
    }

}
