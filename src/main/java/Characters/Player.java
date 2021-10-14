package Characters;
import items.Items;
import item.Item;
import Weapons.Weapon;

public class Player extends Character{
    public Player(Items items, int healthPoints, Weapon weapon) {
        super(items, healthPoints, weapon);
    }
    public <T> void pickUp(T article){
        if (article instanceof Weapon){
            this.setWeapon((Weapon) article);
        }
        if(article instanceof Item){
            this.getItems().addItem((Item) article);
            // need to call hp restortation?
        }
    }
    public <T> void throwItem(T article){
        if (article instanceof Weapon){
            this.setWeapon(null);
        }
        if(article instanceof Item){
            this.getItems().removeItem((Item) article);
        }
    }

}
