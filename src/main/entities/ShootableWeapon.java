package entities;

public abstract class ShootableWeapon extends Weapon {

    private int ammoCount = 0;
    public String id;
    public int ammoPerShot = 1;

    /**
     * Constructs a ShootableWeapon Object
     * @param id the id of the shootable weapon
     * @param ammoCount the amount of ammo that comes with the shootable weapon
     */
    public ShootableWeapon(String id, int ammoCount) {
        super((id),"shoot: obj=shootable1, target=enemy1");
        this.ammoCount = ammoCount;
    }

    public ShootableWeapon(String id) {
        super((id),"shoot: shoot_obj=shootable1, target=enemy1");
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

    /**
     * add ammo to weapon
     *
     * @param ammo int representing number of ammo to be added
     */
    public void addAmmo(int ammo) {
        this.ammoCount = this.ammoCount + ammo;
    }

    /**
     * returns amount of ammo
     *
     * @return int representing ammo amount
     */
    public int getAmmoCount() {
        return this.ammoCount;
    }

    /**
     * removes ammo from ammo amount
     *
     * @param ammo number of ammo to be removed
     */
    public void spendAmmo(int ammo) {
        this.ammoCount = this.ammoCount - ammo;
    }

    /**
     * reset the amount of ammo released per shot
     *
     * @param cost the amount of ammo released per shot
     */
    public void setAmmoPerShot(int cost) {
        this.ammoPerShot = cost;
    }
}
