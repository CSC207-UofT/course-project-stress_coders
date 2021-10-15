package characters;
import playeritems.PlayerItems;
import items.Item;
import weapons.Weapon;

public class Player extends Character{
    public Player(PlayerItems inventory, int healthPoints, Weapon weapon) {
        super(inventory, healthPoints, weapon);
    }
    public <T> void pickUp(T article){
        if (article instanceof Weapon){
            this.setWeapon((Weapon) article);
        }
        if(article instanceof Item){
            this.getInventory().addItem((Item) article);
            // need to call hp restoration?
        }
    }
    public <T> void disposeItem(T article){
        if (article instanceof Weapon){
            this.setWeapon(null);
        }
        if(article instanceof Item){
            this.getInventory().removeItem((Item) article);
        }
    }

}
