package entities.minigames;

import entities.Interactable;
import entities.interfaces.DiceRollable;


public class Teetotum extends Interactable implements DiceRollable {

    private int total;

    public Teetotum(String id, String howToUse) {
        super(id, "role: dice=[teetotum_id]");
        this.total = 0;
    }

    @Override
    public int roll() {
        return 0;
    }

    public boolean rollOver() {
        return false;
    }
}
