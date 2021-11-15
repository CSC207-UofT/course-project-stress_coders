package entities;

import entities.interfaces.Talkable;

/**
 * Goblin.txt abstract class, every goblin is talkable
 */

public class Goblin extends Enemy implements Talkable {

    /**
     * Construct a Goblin
     *
     * @param id the ID for the Goblin object
     * @param player the Player object that the Goblin is interacting with
     * @param value the value of currency gotten when defeating the Goblin
     */
    public Goblin(String id, Player player, int value) {
        super(id, player, value);
public class Goblin extends Interactable implements Talkable {

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
     * @param input the words to be listened to
     * @return what was input with "hehe" in front of it
     */
    @Override

    public String listenAndRespond(String input){return "hehe " + input;}

    public String listenAndRespond(){return "hehe";}

}
