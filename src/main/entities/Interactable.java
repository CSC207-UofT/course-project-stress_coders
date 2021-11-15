package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/*
An object in an encounter that can be used as an argument for a command
 */
public abstract class Interactable {
    // ID/name of the obj, Must be unique among encounters
    private String id;
    private boolean completed;
    private String initialText;
    private final String commandUse;

    /**
    properties that the interactable has as a result of its qualities.
    I.e edible, pushable, throwable all require different variables to be accessed when commands are called
     with those required qualities. I.e a throw command requires a throwable obj that has a weight varaible.
     @see Variable
     For more details.
     **/
    private HashMap<String, Variable> properties = new HashMap<>();

    /**
     * Construct an Interactable
     *
     * @param id the ID of the interactable
     * @param howToUse instructions on howToUse the interactable
     */
    public Interactable(String id, String howToUse){
        this.id = id;
        this.initialText = "";
        this.commandUse = howToUse;
    }

    /**
     * Construct an Interactable
     *
     * @param id the ID of the interactable
     * @param initial the initial text of the interactable
     * @param howToUse instructions on howToUse the interactable
     */
    public Interactable(String id, String initial, String howToUse){
        this.id = id;
        this.initialText = initial;
        this.commandUse = howToUse;
    }

    /**
     * Get the instructions on how to use the Interactable
     *
     * @return the commandUse instructions
     */
    public String getHelp() {return this.commandUse;}

    /**
     * Get the ID of the Interactable
     *
     * @return the id
     */
    public String getId(){
        return this.id;
    }

    /**
     * Get the initial text of the Interactable
     *
     * @return the initialText
     */
    public String getInitialText() {
        return initialText;
    }

    /**
     * Get if the Interactable has been completed or not
     *
     * @return true if the interactable is done
     */
    public boolean isCompleted() { return this.completed; }

    /**
     * Set the completion status of the Interactable
     *
     * @param completed the new status of the Interactable
     */
    public void setCompleted(boolean completed) { this.completed = completed; }

    /**
     * Add a property to the Interactable
     *
     * @param name the property name
     * @param variable the variables of the property
     */
    public void addProperty(String name, Variable variable){
        properties.put(name, variable);
    }

    /**
     * Get the variables of a property from its name
     *
     * @param name the property name
     * @return the variables of the property
     */
    public Variable getProperty(String name){
        return properties.get(name);
    }

    /**
     * Set the ID of the Interactable
     *
     * @param id the new id
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * Get the Hashmap of the properties of the Interactable
     *
     * @return the properties Hashmap
     */
    public HashMap<String, Variable> getProperties(){
        return this.properties;
    }

    /**
     * Set the properties of the Interactable
     *
     * @param props a Hashmap with the new properties of the Interactable
     */
    public void setProperties(HashMap<String, Variable> props){
        this.properties = props;
    }

    /**
     * Set the initial text of the Interactable
     *
     * @param initialText the new initial text
     */
    public void setInitialText(String initialText){
        this.initialText = initialText;
    }

}
