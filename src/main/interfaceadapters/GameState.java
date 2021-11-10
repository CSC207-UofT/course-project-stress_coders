package interfaceadapters;

import usecases.*;

/*
Singleton
Store the current state of encounters: The current encounter, the map of available encounters, the
completed encounters and the required encounters.

Also handle moving between encounters.
Potentially handle saving encounters
 */
public class GameState {

    private Encounter current_encounter = new Encounter();

    public GameState(){

    }

    public Encounter getCurrent_encounter(){
        return this.current_encounter;
    }

}