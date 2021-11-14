package entities;

/*
Generic weapon class all weapons extend
 */
public abstract class Weapon extends Item {

    private int damage = 0;

    public Weapon(String id, String howToUse) {
        super(id,howToUse);
    }

    public Weapon(String id, int damage, String howToUse) {
        super(id, howToUse);
        this.damage = damage;
    }

    public int getDamage() {return this.damage;}

    public void setDamage(int damage){
        this.damage = damage;
    }
}
