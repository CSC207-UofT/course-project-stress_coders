package entities.minigames;


import entities.Interactable;
import entities.interfaces.Launchable;

import java.util.Random;

/**
 * PebbleSkip is an interactable that represents a
 * game where the player tosses a pebble on water in order to
 * get the highest number of skips the pebble can do on the surface of water.
 *
 * The maximum number of skips is 10.
 *
 * The player can try to improve their chances of the pebble skipping a high number of skips
 * by strategically picking an angle to toss the pebble at towards the water.
 */

public class PebbleSkip extends Interactable implements Launchable {

    private final int angle;
    private int numSkips;
    private final int skipWeight;

    /**
     * Construct a new PebbleSkip minigame.
     *
     * The number of skips the pebble makes is randomized,
     * but strategically picking the angle makes improves your chances.
     *
     * The maximum number of skips is 5.
     *
     * Angle must be 0 <= angle <= 90
     *
     * @param id the PebbleSkip's name ID
     */
    public PebbleSkip(String id,  int playerAngle) {
        super(id, "launch: pebble=[pebble_id]");
        Random r = new Random();

        this.skipWeight = r.nextInt(10) + 1;
        this.angle = playerAngle;
        this.calculateNumSkips();
    }


    /**
     * The player Launches the pebble, and we return whether the pebble
     *  was successful in skipping on top of the surface of water or not.
     *
     * if the number of skips > 0, then it succeeded
     *  otherwise if it equals 0, it failed.
     *
     * @return whether the pebble skipped more than zero times.
     */

    @Override
    public boolean launch() {

        /* if the number of skips > 0, then it succeeded return true
           else if it equals 0, return false
         */

        this.setCompleted(true);

        return this.getNumSkips() > 0;
    }

    private void calculateNumSkips() {
        if (this.angle >= 0 && this.angle < 20) {
            this.numSkips = 0;
        } else if (this.angle >= 20 && this.angle < 30) {
            this.numSkips = this.skipWeight / 2;
        } else if (this.angle >= 30 && this.angle <= 90) {
            this.numSkips = this.skipWeight / 4;
        }
    }

    public int getNumSkips() {
        return this.numSkips;
    }

}
