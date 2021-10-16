package characters;
import items.Item;
import weapons.Weapon;
import java.util.List;

public class Player extends Character{

    public Player(List<Item> inventory, int healthPoints, Weapon weapon) {
        super(inventory, healthPoints, weapon);
    }

    public Player() {
        super();
    }

        //Picks up an item from the ground and places it in the Player's inventory
    public <T> void pickUp(T article){
        if (article instanceof Weapon){
            this.setWeapon((Weapon) article);
        }
        if(article instanceof Item){
            this.getInventory().add((Item) article);
            // need to call hp restoration?
        }
    }

        //If the method is called on a weapon, equip it. Else, remove it from the Player's inventory
    public <T> void disposeItem(T article){
        if (article instanceof Weapon){
            this.setWeapon(null);
        }
        if(article instanceof Item){
            this.getInventory().remove((Item) article);
        }
    }

}
