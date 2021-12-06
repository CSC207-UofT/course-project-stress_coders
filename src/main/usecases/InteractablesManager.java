package usecases;

import entities.Interactable;
import entities.Item;
import entities.Variable;
import entities.characters.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class InteractablesManager {
    public HashMap<String, Interactable> objIDs = new HashMap<>();
    ArrayList<Interactable> progression = new ArrayList<>();
    ArrayList<Interactable> genericPool = new ArrayList<>();

    public Interactable getFromID(String ID){
        return objIDs.get(ID);
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

        if(interactable instanceof Item){
            index = random.nextInt(objKeySet.length);
            id = objKeySet[index] + " " + originalId;
        }

        if(interactable instanceof Character){
            ((Character) interactable).addModifier(IDreader.CharAdjectives.get(charKeySet[index]));
        } else {
            for(Variable var : interactable.getProperties().values()){
                float modified_value = var.getInteger() * IDreader.ObjAdjectives.get(objKeySet[index]);
                var.setInteger((int) modified_value);
            }
        }

        interactable.setId(id);
    }
    public boolean containsObj(Interactable item) {
        return this.objIDs.containsKey(item.getId());
    }

    // If object has identical ID either add a number to the end or add some adjective at the beginning
    // We can have a list of ObjectAdjectives.txt, so like Big, red etc. So if there are 2 keys, one can be Big the other red etc.
    // This method only adds main interactables
    public void addObj(Interactable interactable){
        if (interactable instanceof Item) {
            addAdjective(interactable);
            return;
        }

        if (this.objIDs.containsKey(interactable.getId())){
            addAdjective(interactable);
        }
        this.objIDs.put(interactable.getId(), interactable);
        this.progression.add(interactable);
    }
    public void addGeneric(Interactable generic) {
        if (generic instanceof Item) {
            addAdjective(generic);
            return;
        }

        if(this.objIDs.containsKey(generic.getId())){
            addAdjective(generic);
        }
        this.genericPool.add(generic);
        this.objIDs.put(generic.getId() ,generic);
    }
    /**
     * Loads a list of interactables into the encounter
     * @param interactables the list of interactables to add to this encounter, should be ordered and only main
     * interactions should be included
     */
    public void loadInteractables(List<Interactable> interactables) {
        for (Interactable interactable : interactables){
            addObj(interactable);
        }
    }

    /**
     * Returns a list of interactables that can be done in this encounter
     * @returns a list of the interactables as a string of their id's split by \n
     */
    public String listInteractables() {
        StringBuilder out = new StringBuilder();
        for (String key : this.objIDs.keySet()) {
            out.append(objIDs.get(key).getId()).append("\n");
        }
        return out.toString();
    }
}
