package entities;

import java.util.HashMap;
import java.util.Hashtable;

/*
An object in an encounter that can be used as an argument for a command
 */
public abstract class Interactable {
    // ID/name of the obj, Must be unique among encounters
    private String id;
    private boolean completed;
    private final String initialText;
    private final String commandUse;

    /**
    properties that the interactable has as a result of its qualities.
    I.e edible, pushable, throwable all require different variables to be accessed when commands are called
     with those required qualities. I.e a throw command requires a throwable obj that has a weight varaible.
     @see Variable
     For more details.
     **/
    private HashMap<String, Variable> properties = new HashMap<>();

    public Interactable(String id, String howToUse){
        this.id = id;
        this.initialText = "";
        this.commandUse = howToUse;
    }

    public Interactable(String id, String initial, String howToUse){
        this.id = id;
        this.initialText = initial;
        this.commandUse = howToUse;
    }

    public String getHelp() {return this.commandUse;}

    public String getId(){
        return this.id;
    }

    public String getInitialText() {
        return initialText;
    }

    public boolean isCompleted() { return this.completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }

    public void addProperty(String name, Variable variable){
        properties.put(name, variable);
    }

    public Variable getProperty(String name){
        return properties.get(name);
    }

    public void setId(String id){
        this.id = id;
    }

    public HashMap<String, Variable> getProperties(){
        return this.properties;
    }
}
