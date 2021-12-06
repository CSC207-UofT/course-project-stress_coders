package interfaceadapters.commands;

import entities.Interactable;
import usecases.Race;

import java.util.HashMap;

public class RaceCommand extends Command {
    /**
     * Execute the following to play the HorseRace or other Race interactables.
     *
     **/
    final Race race;

    public RaceCommand(){
        this.race = new Race();
    }

    @Override
    public String execute(HashMap<String, Interactable> args) {
        return this.race.playInRace(args);
    }


}
