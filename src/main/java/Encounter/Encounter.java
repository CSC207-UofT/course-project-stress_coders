import java.util;


class Encounter{

        //Instance Variables
        ArrayList on_ground;
        ArrayList characters;
        int size;

public Encounter(int tiles){
        this.size=tiles;

        }

public add_characters(Array[] characters){
        for(int i=0;i<characters.size();i++){
        this.characters.add(characters.get(i));
        }
        }

public add_items(Array[] items){
        for(int i=0;i<items.size();i++){
        this.on_ground.add(items.get(i));
        }
        }

        boolean public picked_up(Object item){
        if on_ground.contains(item){
        on_ground.remove(item);
        return true
        }
        return false
        }

        ArrayList public item_getter() {
        return this.on_ground
        }

        ArrayList public char_getter() {
        return this.characters
        }
        }