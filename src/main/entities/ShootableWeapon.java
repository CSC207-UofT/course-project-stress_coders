package entities;

public class ShootableWeapon extends Weapon {

    private int ammoCount = 0;
    private float hitProb = 100;
    public String id;
    public int damage;
    public int ammoPerShot = 1;

    public ShootableWeapon(String id, int ammoCount, float hitProb, int damage) {
        super((id),"shoot: obj=shootable1, target=enemy1");
        this.ammoCount = ammoCount;
        this.hitProb = hitProb;
        this.damage = damage;
    }

    public ShootableWeapon(String id) {
        super((id),"shoot: shoot_obj=shootable1, target=enemy1");
    }

    public void addHitProb(int hitProb){
        this.hitProb = hitProb;
    }

    public void addDamage(int damage) {
        this.damage = damage;
    }

    public void addAmmo(int ammo) {
        this.ammoCount = this.ammoCount + ammo;
    }

    public int getAmmoCount() {
        return this.ammoCount;
    }

    public int getDamage(){
        return this.damage;
    }

    public void spendAmmo(int ammo) {
        this.ammoCount = this.ammoCount - ammo;
    }

    public float getHitProb(){
        return this.hitProb;
    }

    public void changeAmmoPerShot(int cost) {
        this.ammoPerShot = cost;
    }
}
