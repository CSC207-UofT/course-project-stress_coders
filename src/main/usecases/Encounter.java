package usecases;

import entities.*;
import entities.Character;
import interfaceadapters.IDreader;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private String encounterName;
    private String description;
    private HashMap<String, Interactable> objIDs = new HashMap<String, Interactable>();
    private boolean isCompleted;
    private ArrayList<Interactable> progression = new ArrayList<>();
    private int currEncounterIndex = 0;
    private String initialText;
    private ArrayList<Interactable> genericPool = new ArrayList<>();


    public Encounter(String initialText, String name, String description){
        this.encounterName = name;
        this.description = description;
        Player p = new Player("Sugondeez");
        Axe axe = new Axe("axe");
        Axe axe1 = new Axe("axe");
        Enemy enemy = new Enemy("enemy1", p, 25);
        RiddleGoblin goblin = new RiddleGoblin("goblin1", p, 25);
        goblin.setRiddleInfo("talk to me", "what's the colour of the sky", "blue");
        p.setHealthPoints(100);
        enemy.setHealthPoints(50);
        axe.setHeldBy(p);
        Tree tree = new Tree("tree1");
        this.initialText = initialText;
        addObj((Interactable) axe);
        addObj((Interactable) axe1);
        System.out.println(axe1.getId() + " " + axe1.getProperties().get(InteractableProperties.WEIGHT.name()).getInteger());
        addObj(enemy);
        addObj(goblin);
        addObj(tree);
    }

    public Encounter(List<Interactable> interactables) {
        for (Interactable interactable : interactables){
            addObj(interactable);
        }
    }

    public String getDetails() {
        return this.encounterName + " : " + this.description;
    }

    public String getName() {
        return this.encounterName;
    }

    public void addGeneric(Interactable generic) {
        genericPool.add(generic);
        objIDs.put(generic.getId() ,generic);
    }

    public String loadInitial() {
        return this.initialText;
    }

    public String loadFirstInteractable() {
        return progression.get(0).getInitialText();
    }

    public String progress(HashMap<String, Interactable> userInput, String userCommand) {
        CommandConstants c = new CommandConstants();
        Command needed = c.getCommand(userCommand);
        String s = needed.execute(userInput) + "\n";

        if (progression.get(currEncounterIndex).isCompleted()) {
            if (currEncounterIndex == progression.size()-1) {
                this.isCompleted = true;
                return s  + "Encounter completed, well done!";
            }
            currEncounterIndex++;
            return s+progression.get(currEncounterIndex).getInitialText();
        }
        return s;
    }

    // If object has identical ID either add a number to the end or add some adjective at the beginning
    // We can have a list of ObjectAdjectives.txt, so like Big, red etc. So if there are 2 keys, one can be Big the other red etc.
    public void addObj(Interactable interactable){
        if(this.objIDs.containsKey(interactable.getId())){
            addAdjective(interactable);
        }
        this.objIDs.put(interactable.getId(), interactable);
        this.progression.add(interactable);
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

    public Interactable getFromID(String ID){
        return objIDs.get(ID);
    }

    public boolean isCompleted() {
        return isCompleted;
    }


}
