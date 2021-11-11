package entities;

import entities.interfaces.Talkable;

/**
 * Goblin abstract class, every goblin is talkable
 */
public class Goblin extends Enemy implements Talkable {
    public Goblin(String id, Player player) {
        super(id, player);
    }

    @Override
    public String speak(){return "";};

    @Override
    public String listenAndRespond(String input){return "hehe " + input;};
}
