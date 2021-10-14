package Weapons;

public class Axe extends Weapon {
    public Axe(String name) {
        super(name,25); // 25 HP of damage
    }

    @Override
    public float getProb() {return 0.8f;}
}
