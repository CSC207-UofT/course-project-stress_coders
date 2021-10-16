package encounter;

import java.util.ArrayList;
import java.util.Set;

import items.Item;
import weaponiteminterfaces.CollectableObject;
import characters.Character;


//An encounter is the entity in which almost every in-game action takes place.
public class Encounter{

        //Instance Variables
        ArrayList<CollectableObject> on_ground;
        ArrayList<Character> characters;
        String startup_text;
        Set<String> validActions;

        public Encounter(String txt){
            this.startup_text = txt;

        }

        //Alternative constructor that includes the set valid actions
        public Encounter(String txt, Set<String> actions){
            this.startup_text = txt;
            this.validActions = actions;
        }

        //Add characters to the encounter
         public void addCharacters(ArrayList<Character> characters){
             this.characters.addAll(characters);
        }

        //Add items to the encounter
        public void addItems(ArrayList<CollectableObject> items){
            this.on_ground.addAll(items);
        }

        //Verifies that an item of class CollectableObject is currently on the ground in the encounter and remove it
        //returns true if completed successfully, false otherwise
        public boolean pickUp(CollectableObject item){
            if (on_ground.contains(item)){
                on_ground.remove(item);
                return true;
            }
            return false;
        }

        //Verifies that the input command is in the list of valid commands
        public boolean isValidCommand(String command) {
            return this.validActions.contains(command);
        }

        //Getter for items on the ground in the encounter
        public ArrayList<CollectableObject> getItem() {
            return this.on_ground;
        }

        //Getter for characters in the encounter
        public ArrayList<Character> getChar() {
            return this.characters;
        }

        //Getter for the dialogue of the encounter
        public String getDialogue(){
            return this.startup_text;
        }
}