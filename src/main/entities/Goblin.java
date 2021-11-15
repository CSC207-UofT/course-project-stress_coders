package entities;

import entities.interfaces.Talkable;

/**
 * Goblin abstract class, every goblin is talkable
 */
public class Goblin extends Enemy implements Talkable {
    public Goblin(String id, Player player, int value) {
        super(id, player, value);
    }

    // Extra constructor for the howTo message
    public Goblin(String id, Player player, int value, String howTo) {
        super(id, player, value, howTo);
    }

    @Override
    public String speak(){return "";};

    @Override
    public String listenAndRespond(String input){return "hehe " + input;};
}
