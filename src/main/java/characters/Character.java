package characters;
import items.Item;
import weapons.Weapon;
import java.util.List;

public abstract class Character {
    private List<Item> inventory;
    private int healthPoints;
    private Weapon weapon;

    public Character(){
        this.inventory = null;
        this.healthPoints = 100;
        this.weapon = null;
    }

    public Character(List<Item> inventory, int healthPoints, Weapon weapon){
        this.inventory = inventory;
        this.healthPoints = healthPoints;
        this.weapon = weapon;

    }
    public void setInventory(List<Item> inventory){
        this.inventory = inventory;
    }

    public void setHealthPoints(int healthPoints){
        this.healthPoints = healthPoints;
    }
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
    public List<Item> getInventory(){
        return this.inventory;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

}
