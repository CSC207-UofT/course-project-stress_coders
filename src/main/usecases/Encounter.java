package usecases;

import entities.*;

import java.util.HashMap;
import java.util.List;

/*
Dictate the way a user can interact with the program at any given time. Done so by storing all objects
that can be interacted to represent a given scenario. I.e a fight scenario can be dictated by storing interactable
enemy objects in the encounter, a fishing scenario can be dictated by storing an interactable pond etc.

Also handle when the encounter is completed and how to add new objects into the encounter.
 */
public class Encounter {

    //Maps Interactable ID to the object itself, 'key 1': KeyObject
    public HashMap<String, Interactable> objIDs = new HashMap<String, Interactable>();

    public Encounter(){
        Player p = new Player("Sugondeez");
        Axe axe = new Axe("axe1");
        Enemy enemy = new Enemy("enemy1", p);
        RiddleGoblin goblin = new RiddleGoblin("goblin1", p);
        goblin.setRiddleInfo("talk to me", "what's the colour of the sky", "blue");
        p.setHealthPoints(100);
        enemy.setHealthPoints(50);

        addObj((Interactable) axe);
        addObj(enemy);
        addObj(goblin);
    }

    public Encounter(List<Interactable> interactables) {
        for (Interactable interactable : interactables){
            addObj(interactable);
        }
    }

    // If object has identical ID either add a number to the end or add some adjective at the beginning
    // We can have a list of adjectives, so like Big, red etc. So if there are 2 keys, one can be Big the other red etc.
    public void addObj(Interactable interactable){
        this.objIDs.put(interactable.getId(), interactable);
    }

    public Interactable getFromID(String ID){
        return objIDs.get(ID);
    }
    
    public boolean checkCompletion(){
        boolean completed = true
        for (Interactable interactable : objIDs.values()) {
            if interactable.completed == false {
            completed = false
            }
        }
        return completed
    }
}
