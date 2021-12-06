package entities.weapons;

public abstract class SwingableWeapon extends Weapon{
    /**
     * Constructs a ShootableWeapon Object
     * @param id the id of the shootable weapon
     */

    public SwingableWeapon(String id) {
        super((id),"swing: shoot_obj=swingable1, target=enemy1");
    }

    /**
     * Adds property hit probability with the probability value
     * Must be implemented in child classes
     */
    public abstract void addHitProbability();

    /**
     * Adds property weight with the weight/damage value that is randomized.
     * Must be implemented in child classes.
     */
    public abstract void addWeight();
    public SwingableWeapon(){}

}
