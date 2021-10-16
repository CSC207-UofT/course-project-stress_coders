package characters;
import items.Item;
import weapons.Weapon;
import java.util.List;

public class Player extends Character{

    Weapon previousWeapon;

    public Player(List<Item> inventory, int healthPoints, Weapon weapon) {
        super(inventory, healthPoints, weapon);
        this.previousWeapon = null;
    }

    public Weapon getPreviousWeapon() {return this.previousWeapon;}

    public Player() {
        super();
    }

    public <T> void pickUp(T article){
        if (article instanceof Weapon){
            this.setWeapon((Weapon) article);
        }
        if(article instanceof Item){
            this.getInventory().add((Item) article);
            // need to call hp restoration?
        }
    }


    public <T> void disposeItem(T article){
        if (article instanceof Weapon){
            previousWeapon = (Weapon) article;
            this.setWeapon(null);
        }
        if(article instanceof Item){
            this.getInventory().remove((Item) article);
        }
    }

}
