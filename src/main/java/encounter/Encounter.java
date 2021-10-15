package encounter;

import java.util.ArrayList;
import java.util.Set;

import items.Item;
import characters.Character;



public class Encounter{

        //Instance Variables
        ArrayList<Item> on_ground;
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

         public void add_characters(ArrayList<Character> characters){
             this.characters.addAll(characters);
        }

        public void add_items(ArrayList<Item> items){
            this.on_ground.addAll(items);
        }

        public boolean pick_up(Item item){
            if (on_ground.contains(item)){
                on_ground.remove(item);
                return true;
            }
            return false;
        }

        public boolean isValidCommand(String command) {
            return this.validActions.contains(command);
        }

        public ArrayList<Item> item_getter() {
            return this.on_ground;
        }

        public ArrayList<Character> char_getter() {
            return this.characters;
        }

        public String dialogue_getter(){return this.startup_text;}
}