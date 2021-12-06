package entities;

import entities.weapons.Weapon;

// this class is just for typecasting for players that don't have money
public class Unaffordable extends Weapon {
    public Unaffordable() {
        super("broke", "not Used");
    }
}
