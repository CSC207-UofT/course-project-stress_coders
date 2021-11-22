package entities;

import entities.interfaces.Duelable;

import java.util.Random;


public class Joust extends Interactable implements Duelable {

    Player player;
    boolean defeatedEnemy;
    int defeatedAmount = 50;
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

    /**
     * playerWon returns which character won the joust.
     *
     * As of now, it randomly decides who wins the joust immediately.
     * After the joust winner is decided, we set this.setCompleted(true) to signify that
     * the interactable set has completed.
     *
     * In future, we can make it use more features of Enemy and Player to deal damage and have
     * more rounds, and use them each have weapons such as an Axe or a Lance.
     *
     * @return a character, either the enemy or the player depending on who wins the joust
     */
    @Override
    public Character playerWon() {


        Enemy enemy = new Enemy("joustEnemy", this.player, defeatedAmount);
        Random r = new Random();
        int chosen = r.nextInt(2);

        if (chosen == 0) {
            this.setCompleted(true);
            return enemy;  // player lost the Joust to enemy
        }

        this.setCompleted(true);
        defeatedEnemy = true;
        return player;
        // player won the Joust and defeated enemy, getting valueDefeated defeatedAmount
    }

    /**
     * Return the defeatedAmount field.
     *
     * @return defeatedAmount the amount the player recieves after defeated enemy
     */
    public int getValueDefeated() {

        if (defeatedEnemy) {
            return defeatedAmount;
        }

        return  -1;
    }
}