package entities;

import entities.interfaces.Talkable;

/**
 * Goblin abstract class, every goblin is talkable
 */
public class Goblin extends Interactable implements Talkable {

    // Extra constructor for the howTo message
    public Goblin(String id, String initial, String howTo) {
        super(id, initial, howTo);
    }

    @Override
    public String speak(){return "";}

    @Override
    public String listenAndRespond(){return "hehe";}
}
