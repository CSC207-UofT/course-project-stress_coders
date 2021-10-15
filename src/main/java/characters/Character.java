package characters;
import playeritems.PlayerItems;
import weapons.Weapon;

public abstract class Character {
    private PlayerItems inventory;
    private int healthPoints;
    private Weapon weapon;

    public Character(){
        this.inventory = null;
        this.healthPoints = 100;
        this.weapon = null;
    }

    public Character(PlayerItems inventory, int healthPoints, Weapon weapon){
        this.inventory = inventory;
        this.healthPoints = healthPoints;
        this.weapon = weapon;

    }
    public void setInventory(PlayerItems inventory){
        this.inventory = inventory;
    }

    public void setHealthPoints(int healthPoints){
        this.healthPoints = healthPoints;
    }
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
    public PlayerItems getInventory(){
        return this.inventory;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }
}
