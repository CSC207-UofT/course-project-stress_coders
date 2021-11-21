package entities;

import entities.interfaces.Raceable;

public class HorseRace extends Interactable implements Raceable {

    public HorseRace(String id, String howToUse) {
        super(id, howToUse);
    }

    @Override
    public String[] raceWinners() {
        return new String[0];
    }
}
