package usecases;

import entities.*;
import entities.Character;
import interfaceadapters.IDreader;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
        Axe axe = new Axe("axe");
        Axe axe1 = new Axe("axe");
        Enemy enemy = new Enemy("enemy1", p);
        RiddleGoblin goblin = new RiddleGoblin("goblin1", p);
        goblin.setRiddleInfo("talk to me", "what's the colour of the sky", "blue");
        p.setHealthPoints(100);
        enemy.setHealthPoints(50);

        addObj((Interactable) axe);
        addObj((Interactable) axe1);
        System.out.println(axe1.getId() + " " + axe1.getProperties().get(InteractableProperties.WEIGHT.name()).getInteger());
        addObj(enemy);
        addObj(goblin);
    }

    public Encounter(List<Interactable> interactables) {
        for (Interactable interactable : interactables){
            addObj(interactable);
        }
    }

    // If object has identical ID either add a number to the end or add some adjective at the beginning
    // We can have a list of ObjectAdjectives.txt, so like Big, red etc. So if there are 2 keys, one can be Big the other red etc.
    public void addObj(Interactable interactable){
        if(this.objIDs.containsKey(interactable.getId())){
            addAdjective(interactable);
        }
        this.objIDs.put(interactable.getId(), interactable);
    }

    public void addAdjective(Interactable interactable){
        String id = interactable.getId();
        String originalId = interactable.getId();

        Random random = new Random();

        String[] charKeySet = IDreader.CharAdjectives.keySet().toArray(new String[0]);
        String[] objKeySet = IDreader.ObjAdjectives.keySet().toArray(new String[0]);
        int index = 0;
        while(this.objIDs.containsKey(id)){
            if(interactable instanceof Character) {
                index = random.nextInt(charKeySet.length);
                id = charKeySet[index] + " " + originalId;
            } else {
                index = random.nextInt(objKeySet.length);
                id = objKeySet[index] + " " + originalId;
            }
        }

        if(interactable instanceof Character interactableChar){
            interactableChar.addModifier(IDreader.CharAdjectives.get(charKeySet[index]));
        } else {
            for(Variable var : interactable.getProperties().values()){
                float modified_value = var.getInteger() * IDreader.ObjAdjectives.get(objKeySet[index]);
                var.setInteger((int) modified_value);
            }
        }

        interactable.setId(id);
    }

    public Interactable getFromID(String ID){
        return objIDs.get(ID);
    }
}
