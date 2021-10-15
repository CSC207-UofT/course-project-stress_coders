package encounter;

import java.util.ArrayList;
import java.util.Set;

import items.Item;
import weaponiteminterfaces.CollectableObject;
import characters.Character;



public class Encounter{

        //Instance Variables
        ArrayList<CollectableObject> on_ground;
        ArrayList<Character> characters;
        String startup_text;
        Set<String> validActions;

        public Encounter(String txt){
            this.startup_text = txt;

        }
        public Encounter(String txt, Set<String> actions){
            this.startup_text = txt;
            this.validActions = actions;
        }

         public void addCharacters(ArrayList<Character> characters){
             this.characters.addAll(characters);
        }

        public void addItems(ArrayList<CollectableObject> items){
            this.on_ground.addAll(items);
        }

        public boolean pickUp(CollectableObject item){
            if (on_ground.contains(item)){
                on_ground.remove(item);
                return true;
            }
            return false;
        }

        public boolean isValidCommand(String command) {
            return this.validActions.contains(command);
        }

        public ArrayList<CollectableObject> getItem() {
            return this.on_ground;
        }

        public ArrayList<Character> getChar() {
            return this.characters;
        }

        public String getDialogue(){return this.startup_text;}
}