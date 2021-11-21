package entities;

import entities.interfaces.Duelable;

import java.util.Random;


public class Joust extends Interactable implements Duelable {

    Player player;

    /**
     * Construct a Joust
     *
     * @param id the Joust object's id
     * @param p The current player participating in the joust
     */
    public Joust(String id, Player p) {
        super(id, "duel: joust=[joust_id]");
        player = p;
    }


    @Override
    public boolean playerWon() {
        Random r = new Random();

        int chosen = r.nextInt(2);
        this.setCompleted(true);

        if(chosen == 0) { // chosing the winner randomly.
            return true;  // player wonthe Joust
        } else {
            return false; // player lost the Joust
        }

    }
}