package Weapons;

import interfaces.Collectable;
import interfaces.ThrowableObject;
import Characters.Enemy;

public class Weapon implements ThrowableObject, Collectable {


    /**
     * Weapon of type <entity> is a throwable or collectable item, with
     * Attributes:
     * Weapon name (name - String)
     * Weapon damage (damage - int)
     * Weapon inUse(isCurrent - bool) false by default.
     *
     * Responsibilities:
     * Enforce that the weapon instance of Weapon has the given attributes.
     * Allow weapon to damage Enemies and ensure that it can be interpreted as a package.
     * Allow weapon to be thrown
     * Collaborators: Player
     *
     * **NOTE: For adding weapons,
     * we make them a subclass of this class and have them implement certain interfaces
     */

    private String name;
    private int damage;
    private boolean inUse;
    private float hitProb;


    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
        this.inUse = false;
    }



    public void throwObj(float hitProb) {
        // The probability of weapon hitting a suceesfully hitting a Character.
        this.hitProb = hitProb;
    }

    public float getProb() {
        //return the probability of weapon succesfully hitting a character.
        return this.hitProb;
        //public float getProb() {return 1.0f;}
    }


    public int damageEnemy(Enemy enName) {
        return enName.dealDamage(this.damage);
    }


}
