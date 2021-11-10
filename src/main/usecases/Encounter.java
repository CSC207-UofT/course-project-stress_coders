package usecases;

import entities.*;

import java.util.HashMap;
import java.util.List;

public class Encounter {

    public HashMap<String, Interactable> objIDs = new HashMap<String, Interactable>();

    public Encounter(){
        Player p = new Player("Sugondeez");
        Axe axe = new Axe("axe1");
        Enemy enemy = new Enemy("enemy1", p);
        enemy.setHealthPoints(10);

        addObj((Interactable) axe);
        addObj(enemy);
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
}
