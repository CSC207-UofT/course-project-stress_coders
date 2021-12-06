package entities.minigames;

import entities.Interactable;
import entities.interfaces.Launchable;

import java.util.Random;

/**
 * HorseShoe is an interactable that represents a
 * HorseShoe game where the player can control the
 * distance to toss the horse shoe at a nail.
 *
 *
 * The target nail is 10 units away, and the closer a
 * players horseshoe tosses to it, the more points they get.
 *
 * If the player's points reaches 30 or more, they win.
 *
 */


public class HorseShoe extends Interactable implements Launchable {

    private final int nailDistance;
    private int points;
    private int horseShoeDistance;

    /**
     * Construct a new Horse Shoe toss minigame.
     *
     * The distance of the tosses are randomized.
     *
     * The player must keep calling NewToss until they reach 30 points, otherwise they lose.
     *
     * @param id the HorseShoe's name ID
     */
    public HorseShoe(String id, String howToUse) {
        super(id, "launch: launchable=[horseShoe_id]");

        this.nailDistance = 10;
        this.horseShoeDistance = 0;

    }


    /**
     * Set the distance of their throws eg. 1 unit or 5 unit or 10 units randomly.
     *
     */

    public void setHorseShoeTossedDistance(int distance) {
        this.horseShoeDistance = distance;
    }


    /**
     * Update the points after a few horse shoe tosses.
     *
     */

    public void updatePoints(int pointsToAdd) {
        this.points += pointsToAdd;
    }

    /**
     * Return the points after a few horse shoe tosses.
     *
     * @return the points
     */

    public int getPoints() {
        return this.points;
    }

    /**
     * The player presses W to toss, and then it is randomly calculated how far your
     * horseShoe toss was, and updated the points corresponding to how close the
     *  horse shoe distance was to nailDistance.
     *
     */

    public void newToss() {
        Random r = new Random();
        int tossLength = r.nextInt(nailDistance);
        int pointsToAdd = 0;

        setHorseShoeTossedDistance(tossLength);

        if (tossLength >= 0 && tossLength < 3) {
            pointsToAdd = 3;
        } else if (tossLength >= 3 && tossLength < 6) {
            pointsToAdd = 5;
        } else if (tossLength >= 6 && tossLength < 8) {
            pointsToAdd = 7;
        } else if (tossLength >= 8 && tossLength < 9) {
            pointsToAdd = 9;
        } else {
            // tossLength = 9
            pointsToAdd = 11;
        }

        updatePoints(pointsToAdd);

    }


    /**
     * Return the result of the successive Launches or tosses of the Horse Shoe
     * onto the nails.
     *
     *
     * @return whether the several horse shoe tosses were successful,
     *  ie. if points equals or exceeds 30.
     */

    @Override
    public boolean launch() {

        this.setCompleted(true);

        if (this.getPoints() >= 30) {
            return true;
        }

        return false;
    }



}
