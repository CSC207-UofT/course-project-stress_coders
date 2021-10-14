package Encounter;

import java.util.ArrayList;
import item.Item;
import Characters.Character;



public class Encounter{

        //Instance Variables
        ArrayList<Item> on_ground;
        ArrayList<Character> characters;
        String startup_text;

        public Encounter(String txt){
            this.startup_text = txt;

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

        public ArrayList<Item> item_getter() {
            return this.on_ground;
        }

        public ArrayList<Character> char_getter() {
            return this.characters;
        }
}