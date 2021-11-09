package entities;

import entities.Item;

public class Weapon extends Item {

    private int damage = 0;

    public Weapon(String id) {
        super(id);
    }

    public Weapon(String id, int damage) {
        super(id);
        this.damage = damage;
    }
}
