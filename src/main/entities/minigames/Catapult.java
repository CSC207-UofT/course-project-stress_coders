package entities.minigames;

import entities.Interactable;

import java.util.Random;


/**
 * Catapult is an interactable that represents a
 * Catapult game where the player can control the
 * distance to pull the arm of the catapult back, in order
 * to correctly catapult the rock on to the enemy at a certain distance.
 */


public class Catapult extends Interactable {

    private final int enemyDistance;
    private int pullBackDistance;
    private boolean launched;

    /**
     * Construct a new Catapult which is randomized
     *
     * @param id the Catapult's name ID
     */


    public Catapult(String id) {
        super(id, "launch: catapult=[catapult_id]");
        Random r = new Random();

        this.enemyDistance = r.nextInt(6) + 5;
        this.pullBackDistance = 0;
    }

    /**
     * Construct a new Catapult whose enemy distance is set
     *
     * @param id the Catapult's name ID
     * @param distance the distance of the enemy we want to catapult the rock on top of, which must be at least 5.
     */
    public Catapult(String id, int distance){
        super(id, "launch: catapult=[catapult_id]");
        this.enemyDistance = distance;
        this.pullBackDistance = 0;
    }


    public void setPullBackDistance(int pullBackDistance) {
        this.pullBackDistance = pullBackDistance;
    }

    /**
     * Launch the object from the catapault and return whether the object release
     * was successful in going on top of the enemy or not.
     **/
    public boolean launch() {
        launched = true;
        this.setCompleted(true);

        if(this.distanceWithinPlusMinusOne(this.pullBackDistance, (this.enemyDistance - 5))) {
            return true;
        }
        return false;
    }


    /** Helper method to see whether the distance between a and b is within one.
     *
     * @param a
     * @param b
     * @return whether the distance between a and b is within one.
     */
    private boolean distanceWithinPlusMinusOne(int a, int b) {
        return ((a == b - 1) || (a == b) || (a == b + 1));
    }



}
