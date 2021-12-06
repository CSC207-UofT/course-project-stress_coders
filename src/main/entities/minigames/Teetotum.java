package entities.minigames;

import entities.Interactable;
import entities.interfaces.DiceRollable;


public class Teetotum extends Interactable implements DiceRollable {

    public Teetotum(String id) {
        super(id, "role: dice=[teetotum_id]");
        int total = 0;
    }

    @Override
    public void roll() {
    }

    public boolean rollOver() {
        return false;
    }
}
