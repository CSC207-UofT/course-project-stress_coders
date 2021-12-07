package entities.minigames;

import entities.Interactable;
import entities.interfaces.Launchable;

import java.util.Random;

public class Fishing extends Interactable implements Launchable {


    private final int fishDistance;
    private int pullBackDistance;

    /**
     * Construct a new Fishing minigame, and the
     * fish distance is randomized in Fishing.
     *
     *
     * The player sets the distance of their fishing rod's string in the water,
     * but initially it is zero.
     *
     * @param id the Catapult's name ID
     */


    public Fishing(String id) {
        super(id, "launch: launchable=[fishingRod_id]");
        Random r = new Random();

        this.fishDistance = r.nextInt(10);
        this.pullBackDistance = 0;
    }

    /**
     * The player must call this method to set the length of
     * the fishing rod's string into the water,
     * in order to catch fish.
     *
     * Representation Invariant: 0 <= pullBackDistance <= 10
     */
    public void setPullBackDistance(int pullBackDistance) {
        this.pullBackDistance = pullBackDistance;
    }

    /**
     * Launch the fishing rod's string into the water in order to catch fish.
     *
     * The fishing rod's string catches the fish when the distance between
     * the rod and the fish is two.
     *
     * @return whether the fishing rod caught a fish or not.
     */
    @Override
    public boolean launch() {
        this.setCompleted(true);

        return this.distanceWithinPlusMinusTwo(this.pullBackDistance, (this.fishDistance));

    }

    private boolean distanceWithinPlusMinusTwo(int a, int b) {
        return ((a == b - 2) || (a == b) || (a == b + 2));
    }


}
