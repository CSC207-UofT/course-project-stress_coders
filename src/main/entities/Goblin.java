package entities;

import entities.interfaces.Talkable;

/**
 * Goblin.txt abstract class, every goblin is talkable
 */

public class Goblin extends Interactable implements Talkable {

    /**
     * Construct a Goblin
     *
     * @param id the ID for the Goblin object
     * @param initial the initialText for the interactable
     * @param howTo how to use this interactable
     */

    // Extra constructor for the howTo message
    public Goblin(String id, String initial, String howTo) {
        super(id, initial, howTo);
    }

    /**
     * Return what the goblin's saying
     *
     * @return the Goblin's speech
     */
    @Override
    public String speak(){return "";}

    /**
     * Return how the goblin responds
     *
     * @return "hehe"
     */

    public String listenAndRespond(){return "hehe";}

}
