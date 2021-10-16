package characters;
import items.Item;
import weapons.Weapon;
import java.util.List;

public abstract class Character {
    /**
     * abstarct character with inventory, health points, and weapon.
     */
    private List<Item> inventory;
    private int healthPoints;
    private Weapon weapon;

    /**
     * Creates a new Character with default inventory, health points, and weapon.
     */
    public Character(){
        this.inventory = null;
        this.healthPoints = 100;
        this.weapon = null;
    }

    /**
     * Creates a new Character.
     *
     * @param inventory            the list of items that Character has
     * @param healthPoints         the number of health points of Character
     * @param weapon               weapon of Character
     */
    public Character(List<Item> inventory, int healthPoints, Weapon weapon){
        this.inventory = inventory;
        this.healthPoints = healthPoints;
        this.weapon = weapon;

    }

    /**
     * set the inventory of this Character.
     *
     * @param inventory  list of Items of this Character
     */
    public void setInventory(List<Item> inventory){
        this.inventory = inventory;
    }

    /**
     * set the health points of this Character.
     *
     * @param healthPoints  the number of health points of Character
     */
    public void setHealthPoints(int healthPoints){
        this.healthPoints = healthPoints;
    }

    /**
     * set the weapon of this Character.
     *
     * @param weapon       weapon of this Character
     */
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    /**
     * return the inventory of this Character.
     *
     * @return list of Items
     */
    public List<Item> getInventory(){
        return this.inventory;
    }
    /**
     * return the health points of this Character.
     *
     * @return int that is health points of this character
     */
    public int getHealthPoints() {
        return this.healthPoints;
    }

    /**
     * return the weapon of this Character.
     *
     * @return Weapon of this character
     */
    public Weapon getWeapon() {
        return this.weapon;
    }

}


