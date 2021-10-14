package Characters;
import items.Items;
import Weapons.Weapon;

public abstract class Character {
    private Items items;
    private int healthPoints;
    private Weapon weapon;

    public Character(){
        this.items = null;
        this.healthPoints = 100;
        this.weapon = null;
    }

    public Character(Items items, int healthPoints, Weapon weapon){
        this.items = items;
        this.healthPoints = healthPoints;
        this.weapon = weapon;

    }
    public void setItems(Items items){
        this.items = items;
    }

    public void setHealthPoints(int healthPoints){
        this.healthPoints = healthPoints;
    }
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
    public Items getItems(){
        return this.items;
    }

    public int getHealthPoints() {
        return healthPoints;
    } // should be return this.healthPoints

    public Weapon getWeapon() {
        return this.weapon;
    }
}
